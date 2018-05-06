package arboretum.arboretumwojslawice.View.fragments;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Commons.map.LonLat;
import arboretum.arboretumwojslawice.Commons.map.MapManager;
import arboretum.arboretumwojslawice.Commons.map.PixelCoordinates;
import arboretum.arboretumwojslawice.R;

/**
 * Created by weronika on 22.03.2018.
 */

public class MapFragment extends Fragment {

    /* map variables*/
    int x=0,y=0;
    Bitmap positionMarkerBitmap,canvasBitmap,mapBitmap, toiletMarkerBitmap, viewpointsMarkerBitmap, picnicMarkerBitmap;
    Canvas canvas;
    Resources resources;
    int width, height;
    private ImageView mapImage;
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

        /* map */
        fillCoordinates();
        mapImage = rootView.findViewById(R.id.map);
        resources = getResources();
        //mapBitmap = BitmapFactory.decodeResource(resources, resources.getIdentifier("route_"+route_id, "drawable", getPackageName()));
        mapBitmap = BitmapFactory.decodeResource(resources, resources.getIdentifier("arboretum_map2", "drawable", getActivity().getPackageName()));
        createBitmap();
        drawCanvas();
        //0 -toilets, 1-viewpoints, 2-picnicplaces
        drawMarkers(placesT, 0);
        drawMarkers(placesV, 1);
        drawMarkers(placesP, 2);
        mapImage.setImageBitmap(canvasBitmap);
        /* /map */

        // Inflate the layout for this fragment
        return rootView;
    }

    /* map */
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
        placesT.add(new LonLat(16.865675, 50.709822));

        placesP.add(new LonLat(16.862115, 50.708872));
        placesP.add(new LonLat(16.857734, 50.710267));
        placesP.add(new LonLat(16.860837, 50.711027));
        placesP.add(new LonLat(16.855792, 50.712059));
        placesP.add(new LonLat(16.861059, 50.712497));
        placesP.add(new LonLat(16.858608, 50.713578));

    }

    /* /map */

}
