package arboretum.arboretumwojslawice.View.activities;

import android.Manifest;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import java.util.ArrayList;

import arboretum.arboretumwojslawice.Commons.map.LonLat;
import arboretum.arboretumwojslawice.Commons.map.PixelCoordinates;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.PlantViewModel;

public class PlantLocationMapActivity extends AppCompatActivity{

    public static final String BUNDLE = "BUNDLE";
    public static final String PLANT_ID = "PLANT_ID";
    private int plant_id;
    private PlantViewModel plantViewModel;
    private Bundle bundle;
    private Plant mPlant;

    /* map */
    int x=0,y=0;
    Bitmap positionMarkerBitmap,canvasBitmap,mapBitmap, markerBitmap;
    Canvas canvas;
    ImageView imageview;
    Resources resources;
    int width, height;

    //arboretum
    public final static double MinLat = 50.708060;
    public final static double MaxLat = 50.713493;
    public final static double MinLon = 16.853841;
    public final static double MaxLon = 16.867359;
    /* /map */

    /* test data */
    ArrayList<LonLat> places = new ArrayList<>();
    /* /test data */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_location_map);

        plantViewModel = new PlantViewModel();

        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE);

        plant_id = bundle.getInt(PLANT_ID);
        mPlant = plantViewModel.getPlantById(plant_id); //do mPlant przypisujemy odpowiednią roślinkę

        /* toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_plant_location_map);
        getSupportActionBar().setSubtitle(mPlant.getName());

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        /* /toolbar */

        /* map */
        fillPlantsCoordinates();
        ActivityCompat.requestPermissions(PlantLocationMapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        imageview = findViewById(R.id.map);
        resources = getResources();
        CreateBitmap();

        DrawCanvas();
        drawMarkers(places);
        imageview.setImageBitmap(canvasBitmap);
        /* /map */
    }

    /*map*/
    public void CreateBitmap(){
        positionMarkerBitmap = BitmapFactory.decodeResource(resources,R.drawable.marker_black_big);
        mapBitmap = BitmapFactory.decodeResource(resources,R.drawable.arboretum_map2);
        markerBitmap = BitmapFactory.decodeResource(resources, R.drawable.marker_flower);
        GetBitmapWidthHeight();
        canvasBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565);
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
        double ratioX=((lon-MinLon)*width)/(MaxLon-MinLon);
        x=(int)ratioX;
        return x;
    }

    public int countY(double lat){
        lat=(lat*Math.PI)/180.0;
        double worldMapWidth = ((width/(MaxLon-MinLon))*360)/(2*Math.PI);
        double mapOffsetY=(worldMapWidth/2*Math.log((1+Math.sin(MaxLat*Math.PI/180))/(1-Math.sin(MaxLat*Math.PI/180))));
        y=(int)(height-((worldMapWidth/2*Math.log((1+Math.sin(lat))/(1-Math.sin(lat))))-mapOffsetY));
        return y/4-24;
    }

    public void drawMarkers(ArrayList<LonLat> pointsCoordinates){
        ArrayList<PixelCoordinates> pointsPixelsCoordinates= new ArrayList();
        for(LonLat l: pointsCoordinates){
            pointsPixelsCoordinates.add(new PixelCoordinates(countX(l.lon), countY(l.lat)));
        }
        for (PixelCoordinates p: pointsPixelsCoordinates) {
            canvas.drawBitmap(markerBitmap, p.x, p.y, null);
        }
    }

/* /map */

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    public void fillPlantsCoordinates(){
        places.add(new LonLat(16.856811,50.712181));
        places.add(new LonLat(16.858742,50.712460));
        places.add(new LonLat(16.861521,50.712269));
        places.add(new LonLat(16.863012,50.710591));
        places.add(new LonLat(16.859096,50.710021));
    }
}

