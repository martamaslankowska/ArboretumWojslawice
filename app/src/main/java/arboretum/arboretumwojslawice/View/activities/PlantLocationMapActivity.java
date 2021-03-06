package arboretum.arboretumwojslawice.View.activities;

import android.Manifest;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.Globals;
import arboretum.arboretumwojslawice.Commons.map.LonLat;
import arboretum.arboretumwojslawice.Commons.map.PixelCoordinates;
import arboretum.arboretumwojslawice.Model.businessentity.Location;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.PlantViewModel;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PlantLocationMapActivity extends DaggerAppCompatActivity {

    public static final String BUNDLE = "BUNDLE";
    public static final String PLANT_ID = "PLANT_ID";
    private int plant_id;
    float scale = 1.0f;


    @Inject
    protected PlantViewModel plantViewModel;
    private Bundle bundle;
    private CompositeDisposable compositeDisposable;

    /* map variables*/
    int x=0,y=0;
    Bitmap positionMarkerBitmap,canvasBitmap,mapBitmap, markerBitmap;
    Canvas canvas;
    ImageView imageview;
    Resources resources;
    int width, height;


    List<LonLat> places = new ArrayList<>();
    List<Location> locations;

    //arboretum
    public final static double MinLat = 50.708060;
    public final static double MaxLat = 50.713493;
    public final static double MinLon = 16.853841;
    public final static double MaxLon = 16.867359;
    /* /map variables*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_location_map);
        compositeDisposable = new CompositeDisposable();

        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE);
        plant_id = bundle.getInt(PLANT_ID);


        /* toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_plant_location_map);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        /* /toolbar */

        ActivityCompat.requestPermissions(PlantLocationMapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        imageview = findViewById(R.id.map);
        PhotoViewAttacher photoView = new PhotoViewAttacher(imageview);
        photoView.setScale(scale, true);
        photoView.update();
        resources = getResources();
        CreateBitmap();
        DrawCanvas();


        Disposable cdLocations = Maybe.fromCallable(() -> {
            return plantViewModel.getPlantLocations(plant_id);
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(locationList -> {
                            locations = locationList;

                              /* map */
                            fillPlantsCoordinates();
                            drawMarkers(places);
                            imageview.setImageBitmap(canvasBitmap);
        /* /map */
                        }
                        ,throwable -> {
                            /* onError() */
                            Toast.makeText(this, "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG).show();
                        });

        compositeDisposable.add(cdLocations);

    }


    public void CreateBitmap(){
        positionMarkerBitmap = BitmapFactory.decodeResource(resources,R.drawable.ic_marker_black_big);
        mapBitmap = BitmapFactory.decodeResource(resources,R.drawable.full_map);
        markerBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_marker_flower);
        GetBitmapWidthHeight();
        canvasBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
    }

    public void GetBitmapWidthHeight(){
        width = mapBitmap.getWidth();
        height = mapBitmap.getHeight();
    }

    public void DrawCanvas(){
        canvas = new Canvas(canvasBitmap);
        canvas.drawBitmap(mapBitmap,0,0,null);
    }

    public int countX(double lon){
        double ratioX=((Globals.MaxLon-lon)*width)/(Globals.MaxLon-Globals.MinLon);
        x=(int)ratioX;
        return x;
    }

    public int countY(double lat){
        double ratioY=((lat - Globals.MinLat)*height)/(Globals.MaxLat-Globals.MinLat);
        y=(int)ratioY;
        return y+110;
    }

    public void drawMarkers(List<LonLat> pointsCoordinates){
        if(pointsCoordinates.size()!=0) {
            ArrayList<PixelCoordinates> pointsPixelsCoordinates = new ArrayList();
            for (LonLat l : pointsCoordinates) {
                pointsPixelsCoordinates.add(new PixelCoordinates(countX(l.lon), countY(l.lat)));
            }
            for (PixelCoordinates p : pointsPixelsCoordinates) {
                canvas.drawBitmap(markerBitmap, p.x, p.y, null);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    public void fillPlantsCoordinates(){
        for(Location l:locations){
            places.add(new LonLat(l.getY(), l.getX()));
        }
    }
}

