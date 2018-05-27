package arboretum.arboretumwojslawice.View.fragments;

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
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Commons.Globals;
import arboretum.arboretumwojslawice.Commons.map.LonLat;
import arboretum.arboretumwojslawice.Commons.map.MapManager;
import arboretum.arboretumwojslawice.Commons.map.PixelCoordinates;
import arboretum.arboretumwojslawice.R;
import uk.co.senab.photoview.PhotoViewAttacher;


public class MapFragment extends Fragment implements LocationListener {

    /* map variables*/
    double lon=0, lat=0;
    int x=0,y=0;
    Bitmap positionMarkerBitmap,canvasBitmap,mapBitmap, toiletMarkerBitmap, viewpointsMarkerBitmap, picnicMarkerBitmap, parkingMarkerBitmap;
    Canvas canvas;
    Resources resources;
    int width, height;
    private ImageView mapImage;
    float baseScale = 1.0f;

    List<LonLat> placesT = new ArrayList<>();
    List<LonLat> placesV = new ArrayList<>();
    List<LonLat> placesP = new ArrayList<>();

    //zoom 1
    float scale=1.0f;
    List<LonLat> placesToilet1 = new ArrayList<>();
    List<LonLat> placesViewpoints1 = new ArrayList<>();
    List <LonLat> placesPicnic1 = new ArrayList<>();

    //zoom2
    float scale2=1.8f;
    List<LonLat> placesViewpoints2 = new ArrayList<>();
    List <LonLat> placesPicnic2 = new ArrayList<>();

    //zoom3
    float scale3=2.5f;
    List<LonLat> placesViewpoints3 = new ArrayList<>();
    List <LonLat> placesPicnic3 = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        /* location */
        lon=16.856737;
        lat=50.711166;
        Location l = getLocation();
        if (l != null) {
            lat = l.getLatitude();
            lon = l.getLongitude();
        }
        x=countX(lon);
        y=countY(lat);
        /* /location */
        /* map */

        fillCoordinates();
        mapImage = rootView.findViewById(R.id.map);
        PhotoViewAttacher photoView = new PhotoViewAttacher(mapImage);
        photoView.setScale(baseScale, true);
        photoView.update();
        resources = getResources();
        //mapBitmap = BitmapFactory.decodeResource(resources, resources.getIdentifier("route_"+route_id, "drawable", getPackageName()));
        mapBitmap = BitmapFactory.decodeResource(resources, resources.getIdentifier("arboretum_map3", "drawable", getActivity().getPackageName()));
        createBitmap();
        drawCanvas();
        drawPositionMarker(x,y);
        canvas.drawBitmap(parkingMarkerBitmap, countX(16.856991), countY(50.712718), null);
        //0 -toilets, 1-viewpoints, 2-picnicplaces
//        drawMarkers(placesToilet1, 0);
//        drawMarkers(placesViewpoints1, 1);
//        drawMarkers(placesPicnic1, 2);
        mapImage.setImageBitmap(canvasBitmap);


        photoView.setOnScaleChangeListener(new PhotoViewAttacher.OnScaleChangeListener() {
            @Override
            public void onScaleChange(float v, float v1, float v2) {
               scale = photoView.getScale();
               if(scale>scale2){
                   createBitmap();
                   drawCanvas();
//                   drawMarkers(placesToilet1, 0);
//                   drawMarkers(placesViewpoints1, 1);
//                   drawMarkers(placesPicnic1, 2);
//
//                   drawMarkers(placesViewpoints2, 1);
//                   drawMarkers(placesPicnic2, 2);

                   mapImage.setImageBitmap(canvasBitmap);
               }
                if(scale>scale3) {
                    createBitmap();
                    drawCanvas();
//                    drawMarkers(placesToilet1, 0);
//                    drawMarkers(placesViewpoints1, 1);
//                    drawMarkers(placesPicnic1, 2);
//
//                    drawMarkers(placesViewpoints2, 1);
//                    drawMarkers(placesPicnic2, 2);
//
//                    drawMarkers(placesViewpoints3, 1);
//                    drawMarkers(placesPicnic3, 2);
                    mapImage.setImageBitmap(canvasBitmap);
                }
            }
        });

        /* /map */
        return rootView;
    }


    public void createBitmap(){
        positionMarkerBitmap = BitmapFactory.decodeResource(resources,R.drawable.ic_marker_black_big);
        toiletMarkerBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_marker_toilet);
        viewpointsMarkerBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_marker_eye);
        picnicMarkerBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_marker_picnic);
        parkingMarkerBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_marker_parking);
        GetBitmapWidthHeight();
        canvasBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565);
    }

    public void GetBitmapWidthHeight(){
        width = mapBitmap.getWidth();
        height = mapBitmap.getHeight();
    }

    public void drawCanvas(){
        canvas = new Canvas(canvasBitmap);
        canvas.drawBitmap(mapBitmap,0,0,null);
    }

    public int countX(double lon){
        double ratioX=((lon- Globals.MinLon)*width)/(Globals.MaxLon-Globals.MinLon);
        //double ratioX=((Globals.MaxLon - lon)*width)/(Globals.MaxLon-Globals.MinLon);
        x=(int)ratioX;
        return x-100;
    }

    public int countY(double lat){
        lat=(lat*Math.PI)/180.0;
        double worldMapWidth = ((width/(Globals.MaxLon-Globals.MinLon))*360)/(2*Math.PI);
        double mapOffsetY=(worldMapWidth/2*Math.log((1+Math.sin(Globals.MaxLat*Math.PI/180))/(1-Math.sin(Globals.MaxLat*Math.PI/180))));
        y=(int)(height-((worldMapWidth/2*Math.log((1+Math.sin(lat))/(1-Math.sin(lat))))-mapOffsetY));
        return height-(y/4+24);
    }

//    public int countY(double lat){
//        lat=(lat*Math.PI)/180.0;
//        double worldMapWidth = ((width/(Globals.MaxLon-Globals.MinLon))*360)/(2*Math.PI);
//        double mapOffsetY=(worldMapWidth/2*Math.log((1+Math.sin(Globals.MaxLat*Math.PI/180))/(1-Math.sin(Globals.MaxLat*Math.PI/180))));
//        y=(int)(height-((worldMapWidth/2*Math.log((1+Math.sin(lat))/(1-Math.sin(lat))))-mapOffsetY));
//        return y/4+300;
//    }

    public void drawPositionMarker(int x, int y){
        canvas.drawBitmap(positionMarkerBitmap, x, y, null);
    }

    public void drawMarkers(List<LonLat> pointsCoordinates, int type){
        ArrayList<PixelCoordinates> pointsPixelsCoordinates= new ArrayList();
        if(type==0) {
            ArrayList<PixelCoordinates> toiletsCoordinates= new ArrayList();
            for(LonLat l: pointsCoordinates){
                toiletsCoordinates.add(new PixelCoordinates(countX(l.lon), countY(l.lat)));
            }
            for (PixelCoordinates p : toiletsCoordinates) {
                canvas.drawBitmap(toiletMarkerBitmap, p.x, p.y, null);
            }
        }else{
        if(type==1) {
            ArrayList<PixelCoordinates> viewPointsCoordinates= new ArrayList();
            for(LonLat l: pointsCoordinates){
                viewPointsCoordinates.add(new PixelCoordinates(countX(l.lon), countY(l.lat)));
            }
            for (PixelCoordinates p : viewPointsCoordinates) {
                canvas.drawBitmap(viewpointsMarkerBitmap, p.x, p.y, null);
            }
        }else
        if(type==2) {
            ArrayList<PixelCoordinates> picnicPlacesCoordinates= new ArrayList();
            for(LonLat l: pointsCoordinates){
                picnicPlacesCoordinates.add(new PixelCoordinates(countX(l.lon), countY(l.lat)));
            }
            for (PixelCoordinates p : picnicPlacesCoordinates) {
                canvas.drawBitmap(picnicMarkerBitmap, p.x, p.y, null);
            }
        }
        }
    }

    public void fillCoordinates(){
        placesToilet1.add(new LonLat(16.857349,50.712130));
        placesToilet1.add(new LonLat(16.859760,50.712175));
        placesViewpoints1.add(new LonLat(16.854539,50.711776));
        placesViewpoints1.add(new LonLat(16.858177,50.710130));
        placesViewpoints1.add(new LonLat(16.862673,50.709100));
        placesViewpoints1.add(new LonLat(16.861043,50.712136));
        placesViewpoints1.add(new LonLat(16.862775,50.712671));
        placesPicnic1.add(new LonLat(16.865043,50.710777));
        placesPicnic1.add(new LonLat(16.860442,50.709297));
        placesPicnic1.add(new LonLat(16.854293,50.711144));

        placesViewpoints2.add(new LonLat(16.854021,50.711426));
        placesViewpoints2.add(new LonLat(16.864572,50.711924));
        placesPicnic2.add(new LonLat(16.863050,50.711771));
        placesPicnic2.add(new LonLat(16.854834,50.711344));

        placesViewpoints3.add(new LonLat(16.854424,50.710728));
        placesViewpoints3.add(new LonLat(16.856648,50.710913));
        placesPicnic3.add(new LonLat(16.855605,50.711489));
        placesPicnic3.add(new LonLat(16.859766,50.712394));
        placesPicnic3.add(new LonLat(16.862847,50.712412));
        placesPicnic3.add(new LonLat(16.863310,50.711148));
    }

    public Location getLocation(){
        Context context = getActivity().getApplicationContext();
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            //Toast.makeText(context, "brak pozwolenia na GPS", Toast.LENGTH_SHORT).show();
            //turnGpsOn();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0,this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        }else{
            Toast.makeText(context, "włącz GPS", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
        return null;
    }

    private void turnGpsOn(){
        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }


    @Override
    public void onLocationChanged(Location location) {
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
}
