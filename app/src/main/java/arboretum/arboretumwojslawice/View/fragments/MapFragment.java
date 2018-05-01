package arboretum.arboretumwojslawice.View.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import arboretum.arboretumwojslawice.R;

/**
 * Created by weronika on 22.03.2018.
 */

public class MapFragment extends Fragment {

    ImageView imageview;
    int x=0,y=0;
    Bitmap markerBitmap, mapBitmap, canvasBitmap;
    Canvas canvas;
    int width, height;

    //arboretum
    public final static double MinLat = 50.708060;
    public final static double MaxLat = 50.713493;
    public final static double MinLon = 16.853841;
    public final static double MaxLon = 16.867359;


    //restaurant
    double lon=16.856737;
    double lat=50.711166;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        imageview = rootView.findViewById(R.id.map);
        mapBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.arboretum_map2);
        imageview.setImageBitmap(mapBitmap);

        //canvasBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565);
        markerBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_location);

        // Inflate the layout for this fragment
        return rootView;
    }

    public void GetBitmapWidthHeight(){
        width = mapBitmap.getWidth();
        height = mapBitmap.getHeight();
        //Toast.makeText(getApplicationContext(), width + "\n" +  height, Toast.LENGTH_SHORT).show();
    }

    public void DrawCanvas(int x, int y){
        canvas = new Canvas(canvasBitmap);
        canvas.drawBitmap(mapBitmap,0,0,null);
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
        return y/4;
    }

}
