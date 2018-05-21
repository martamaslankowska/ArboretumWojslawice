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

import arboretum.arboretumwojslawice.Commons.map.LonLat;
import arboretum.arboretumwojslawice.Commons.map.MapManager;
import arboretum.arboretumwojslawice.Commons.map.PixelCoordinates;
import arboretum.arboretumwojslawice.R;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by weronika on 22.03.2018.
 */

public class MapFragment extends Fragment implements LocationListener {

    /* map variables*/
    double lon=0, lat=0;
    int x=0,y=0;
    Bitmap positionMarkerBitmap,canvasBitmap,mapBitmap, toiletMarkerBitmap, viewpointsMarkerBitmap, picnicMarkerBitmap;
    Canvas canvas;
    Resources resources;
    int width, height;
    private ImageView mapImage;
    double scale = 2.3206200;
    double scale_pom = 1.6206200;
    float scale2 = (float) scale_pom;
    //test
    List<LonLat> placesT = new ArrayList<>();
    List<LonLat> placesV = new ArrayList<>();
    List<LonLat> placesP = new ArrayList<>();


    //arboretum
    public final static double MinLat = 50.708060;
    public final static double MaxLat = 50.713493;
    public final static double MinLon = 16.853841;
    public final static double MaxLon = 16.867359;
    /* /map variables*/


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
//        Location l = getLocation();
//        if (l != null) {
//            lat = l.getLatitude();
//            lon = l.getLongitude();
//        }
        x=countX(lon);
        y=countY(lat);
        /* /location */
        /* map */
        fillCoordinates();
        mapImage = rootView.findViewById(R.id.map);
        PhotoViewAttacher photoView = new PhotoViewAttacher(mapImage);
        photoView.setScale((float)scale, true);
        photoView.update();
        resources = getResources();
        //mapBitmap = BitmapFactory.decodeResource(resources, resources.getIdentifier("route_"+route_id, "drawable", getPackageName()));
        mapBitmap = BitmapFactory.decodeResource(resources, resources.getIdentifier("arboretum_map2", "drawable", getActivity().getPackageName()));
        createBitmap();
        drawCanvas();
        //0 -toilets, 1-viewpoints, 2-picnicplaces
        //drawMarkers(placesT, 0);
        //drawMarkers(placesV, 1);
        drawMarkers(placesP, 2);
        drawPositionMarker(y,x);
        mapImage.setImageBitmap(canvasBitmap);


        photoView.setOnScaleChangeListener(new PhotoViewAttacher.OnScaleChangeListener() {
            @Override
            public void onScaleChange(float v, float v1, float v2) {
               scale = photoView.getScale();
               if(scale<2.5){
                   createBitmap();
                   drawCanvas();
                   drawMarkers(placesV, 1);
                   mapImage.setImageBitmap(canvasBitmap);
               }
                if(scale>2.5) {
                    createBitmap();
                    drawCanvas();
                    drawMarkers(placesT, 0);
                    mapImage.setImageBitmap(canvasBitmap);
                }
            }
        });

        /* /map */

        // Inflate the layout for this fragment
        return rootView;
    }


    public void createBitmap(){
        positionMarkerBitmap = BitmapFactory.decodeResource(resources,R.drawable.ic_marker_black_big);
        toiletMarkerBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_marker_toilet);
        viewpointsMarkerBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_marker_eye);
        picnicMarkerBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_marker_picnic);
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
        placesV.add(new LonLat(16.856811,50.712181));
        placesV.add(new LonLat(16.858742,50.712460));
        placesV.add(new LonLat(16.861521,50.712269));
        placesV.add(new LonLat(16.863810,50.711869));

        placesT.add(new LonLat(16.857109, 50.711267));
        placesT.add(new LonLat(16.861424, 50.712001));
        placesT.add(new LonLat(16.863675, 50.709822));

        placesP.add(new LonLat(16.862115, 50.708872));
        placesP.add(new LonLat(16.857734, 50.710267));
        placesP.add(new LonLat(16.860837, 50.711027));
        placesP.add(new LonLat(16.855792, 50.712059));
        placesP.add(new LonLat(16.861059, 50.712497));
        placesP.add(new LonLat(16.858608, 50.713578));
    }

    public Location getLocation(){
        Context context = getActivity().getApplicationContext();
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
