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

import arboretum.arboretumwojslawice.Model.businessentity.Route;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.RouteViewModel;
import dagger.android.support.DaggerAppCompatActivity;

public class NavigationActivity extends DaggerAppCompatActivity implements LocationListener{

    private static final String BUNDLE = "BUNDLE";
    private static final String ROUTE_ID = "ROUTE_ID";

    @Inject
    protected RouteViewModel routeViewModel;
    private Bundle bundle;
    private Route mRoute;
    private int route_id;

    int x=0,y=0;
    Bitmap bitmap1,bitmap2,bitmap3;
    Canvas canvas;
    ImageView imageview;
    Resources resources;
    int width, height;

    //arboretum
    public final static double MinLat = 50.708060;
    public final static double MaxLat = 50.713493;
    public final static double MinLon = 16.853841;
    public final static double MaxLon = 16.867359;

    //dom
//    public final static double MinLat = 51.092267;
//    public final static double MaxLat = 51.092772;
//    public final static double MinLon = 17.001062;
//    public final static double MaxLon = 17.002220;


    //restaurant
    double lon=16.856737;
    double lat=50.711166;
    //double lon,lat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE);

        route_id = bundle.getInt(ROUTE_ID);
        mRoute = routeViewModel.getRouteById(route_id); //do mRoute przypisujemy odpowiednią trasę

        /* toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle(R.string.toolbar_navigation);
        getSupportActionBar().setSubtitle(mRoute.getName());
        /* /toolbar */

        ActivityCompat.requestPermissions(NavigationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        //uncomment to check real position
//        Location l = getLocation();
//        if (l != null) {
//            lat = l.getLatitude();
//            lon = l.getLongitude();
//        }

        imageview = findViewById(R.id.map);
        resources = getResources();
        CreateBitmap();
        GetBitmapWidthHeight();
        bitmap2 = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565);

        x=countX(lon);
        y=countY(lat);
        Toast.makeText(getApplicationContext(), x + "\n" +  y, Toast.LENGTH_SHORT).show();
        DrawCanvas();
        drawMarker(x,y);
        imageview.setImageBitmap(bitmap2);

    }

   public Location getLocation(){
        Context context = getApplicationContext();
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            //Toast.makeText(context, "brak pozwolenia na GPS", Toast.LENGTH_SHORT).show();
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
        int y=countY(lat);
        drawMarker(x,y);
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

    public void CreateBitmap(){
        bitmap1 = BitmapFactory.decodeResource(resources,R.drawable.ic_marker_black_big);
        bitmap3 = BitmapFactory.decodeResource(resources,R.drawable.arboretum_map2);
    }

    public void GetBitmapWidthHeight(){
        width = bitmap3.getWidth();
        height = bitmap3.getHeight();
        //Toast.makeText(getApplicationContext(), width + "\n" +  height, Toast.LENGTH_SHORT).show();
    }

    public void DrawCanvas(){
        canvas = new Canvas(bitmap2);
        canvas.drawBitmap(bitmap3,0,0,null);
    }

    public void drawMarker(int x, int y){
        canvas.drawBitmap(bitmap1,x,y,null);
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
}
