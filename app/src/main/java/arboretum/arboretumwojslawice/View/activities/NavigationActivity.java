package arboretum.arboretumwojslawice.View.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.DividerItemDecoration;
import arboretum.arboretumwojslawice.Model.businessentity.Route;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.RouteViewModel;
import arboretum.arboretumwojslawice.databinding.FragmentRouteBinding;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import uk.co.senab.photoview.PhotoViewAttacher;

public class NavigationActivity extends DaggerAppCompatActivity implements LocationListener{

    private static final String BUNDLE = "BUNDLE";
    private static final String ROUTE_ID = "ROUTE_ID";

    @Inject
    protected RouteViewModel routeViewModel;
    protected CompositeDisposable compositeDisposable;

    private Bundle bundle;
    private Route mRoute;
    private int route_id;
    
    int x=0,y=0;
    Bitmap positionMarkerBitmap,canvasBitmap,mapBitmap, markerBitmap;
    Canvas canvas;
    Resources resources;
    int width, height;
    private ImageView mapImage;
    double scale = 2.3206200;

    //arboretum
    public final static double MinLat = 50.708060;
    public final static double MaxLat = 50.713493;
    public final static double MinLon = 16.853841;
    public final static double MaxLon = 16.867359;


    //restaurant
//    double lon=16.856737;
//    double lat=50.711166;


    double lon,lat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE);
        compositeDisposable = new CompositeDisposable();

        route_id = bundle.getInt(ROUTE_ID);



        Disposable cdRoutes = Maybe.fromCallable(() -> {
            return routeViewModel.getById(route_id);
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(routes -> {
                            Route route = routes;

                            /* toolbar */
                            Toolbar toolbar = findViewById(R.id.toolbar_back);
                            setSupportActionBar(toolbar);

                            if (getSupportActionBar() != null) {
                                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                                getSupportActionBar().setDisplayShowHomeEnabled(true);
                            }
                            getSupportActionBar().setTitle(R.string.toolbar_navigation);
                            getSupportActionBar().setSubtitle(route.getName());
                            /* /toolbar */
                        }
                        ,throwable -> {
                            /* onError() */
                            Toast.makeText(this, "Jakiś błąąąd w nawigacji, hehe...", Toast.LENGTH_LONG).show();
                        });

        compositeDisposable.add(cdRoutes);



        ActivityCompat.requestPermissions(NavigationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        //uncomment to check real position
        Location l = getLocation();
        if (l != null) {
            lat = l.getLatitude();
            lon = l.getLongitude();
        }

        mapImage = findViewById(R.id.map);
        PhotoViewAttacher photoView = new PhotoViewAttacher(mapImage);
        photoView.setScale((float)scale, true);
        photoView.update();
        resources = getResources();
        createBitmap();
        getBitmapWidthHeight();
        canvasBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565);
        mapBitmap = BitmapFactory.decodeResource(resources, resources.getIdentifier("arboretum_map2", "drawable", getPackageName()));
        x=countX(lon);
        y=countY(lat);
        createBitmap();
        drawCanvas();
        drawMarker(x,y);
        mapImage.setImageBitmap(canvasBitmap);
    }

   public Location getLocation(){
        Context context = getApplicationContext();
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            Toast.makeText(context, "brak pozwolenia na GPS", Toast.LENGTH_SHORT).show();
            turnGpsOn();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0,this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        }else{
            //Toast.makeText(context, "włącz GPS", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
        return null;
    }

    private void turnGpsOn(){
        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }

    @Override
    public void onLocationChanged(Location location) {
        double lon = location.getLongitude();
        double lat = location.getLatitude();
        int x = countX(lon);
        int y = countY(lat);
        mapBitmap = BitmapFactory.decodeResource(resources, resources.getIdentifier("arboretum_map2", "drawable", getPackageName()));
        drawCanvas();
        drawMarker(x, y);
        mapImage.setImageBitmap(canvasBitmap);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }

    public void createBitmap(){
        markerBitmap = BitmapFactory.decodeResource(resources,R.drawable.ic_marker_black_big);
        mapBitmap = BitmapFactory.decodeResource(resources,R.drawable.arboretum_map2);
        getBitmapWidthHeight();
        canvasBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565);
    }

    public void getBitmapWidthHeight(){
        width = mapBitmap.getWidth();
        height = mapBitmap.getHeight();
    }

    public void drawCanvas(){
        canvas = new Canvas(canvasBitmap);
        canvas.drawBitmap(mapBitmap,0,0,null);
    }

    public void drawMarker(int x, int y){
        canvas.drawBitmap(markerBitmap,x,y,null);
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

    private void turnGPSOn(){
        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", true);
        sendBroadcast(intent);
    }

        public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    public void getQRCode(View view) {
        Intent intent = new Intent(this, QRCodeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
