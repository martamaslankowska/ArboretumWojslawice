package arboretum.arboretumwojslawice.Commons.map;

import android.Manifest;
import android.app.Activity;
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
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.activities.NavigationActivity;
import arboretum.arboretumwojslawice.View.activities.PlantLocationMapActivity;

public class MapManager implements LocationListener{

    public MapManager(PlantLocationMapActivity act){
        activity=act;
    }

    public static Bitmap mapBitmap,positionMarkerBitmap,markerBitmap, canvasBitmap;
    public static Canvas canvas;
    public static ImageView imageview;
    public static Context c;
    public static PlantLocationMapActivity activity;
    int width, height;
    //LonLat coordinates;
    //PixelCoordinates pixelCoordinates;

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
    LonLat coordinates = new LonLat(16.856737, 50.711166);
    PixelCoordinates pixelCoordinates = new PixelCoordinates(countX(coordinates.lon), countY(coordinates.lat));


    public Location getLocation(Context c){
        Context context = c;
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(context, "brak pozwolenia na GPS", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0,this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        }else{
            turnGPSOn(c);
            Toast.makeText(context, "inaczej chyba się nie da...", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        coordinates.lon = location.getLongitude();
        coordinates.lat = location.getLatitude();
        pixelCoordinates.x = countX(coordinates.lon);
        pixelCoordinates.y = countY(coordinates.lat);
    }

    private void turnGPSOn(Context c){
        c.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
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

    public void createBitmaps(Context c, int width, int height){
        positionMarkerBitmap = BitmapFactory.decodeResource(c.getResources(), R.drawable.marker_black_big);
        mapBitmap = BitmapFactory.decodeResource(c.getResources(), R.drawable.arboretum_map2);
        canvasBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565);
        markerBitmap = BitmapFactory.decodeResource(c.getResources(), R.drawable.marker_black);
    }

    public PixelCoordinates getMapBitmapWidthHeight(){
        width = mapBitmap.getWidth();
        height = mapBitmap.getHeight();
        //Toast.makeText(getApplicationContext(), width + "\n" +  height, Toast.LENGTH_SHORT).show();
        return new PixelCoordinates(width, height);
    }

    public void drawCanvas(){
        canvas = new Canvas(canvasBitmap);
        canvas.drawBitmap(mapBitmap,0,0,null);
    }

    public void drawMyPosition(int x, int y){
        canvas.drawBitmap(positionMarkerBitmap,x,y,null);
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

    public int countX(double lon){
        double ratioX=((lon-MinLon)*width)/(MaxLon-MinLon);
        pixelCoordinates.x=(int)ratioX;
        return pixelCoordinates.x;
    }

    public int countY(double lat){
        lat=(lat*Math.PI)/180.0;
        double worldMapWidth = ((width/(MaxLon-MinLon))*360)/(2*Math.PI);
        double mapOffsetY=(worldMapWidth/2*Math.log((1+Math.sin(MaxLat*Math.PI/180))/(1-Math.sin(MaxLat*Math.PI/180))));
        pixelCoordinates.y=(int)(height-((worldMapWidth/2*Math.log((1+Math.sin(lat))/(1-Math.sin(lat))))-mapOffsetY));
        return pixelCoordinates.y/4-24;
    }

    public void setCoordinates(double lon, double lat){
        this.coordinates.lon = lon;
        this.coordinates.lat = lat;
    }

    public LonLat getCoordinates(){
        return coordinates;
    }

    public double getLon(){
        return coordinates.lon;
    }

    public double getLat(){
        return coordinates.lat;
    }

    public Bitmap getCanvasBitmap(){
        return canvasBitmap;
    }

}
