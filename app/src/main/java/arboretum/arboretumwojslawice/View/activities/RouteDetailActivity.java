package arboretum.arboretumwojslawice.View.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.DividerItemDecoration;
import arboretum.arboretumwojslawice.Commons.map.LonLat;
import arboretum.arboretumwojslawice.Commons.map.PixelCoordinates;
import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Location;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.Model.businessentity.PointOnRoute;
import arboretum.arboretumwojslawice.Model.businessentity.Route;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.adapter.ViewPagerAdapter;
import arboretum.arboretumwojslawice.ViewModel.RouteDetailViewModel;
import arboretum.arboretumwojslawice.databinding.ActivityRouteDetailBinding;
import arboretum.arboretumwojslawice.databinding.FragmentRouteBinding;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RouteDetailActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    public static final String BUNDLE = "BUNDLE";
    public static final String ROUTE_ID = "ROUTE_ID";
    public static final String PLANT_ID = "PLANT_ID";
    public static final String TAB_ID = "TAB_ID";

    private Bundle bundle;
    private int route_id;
    private Route route;
    private List<Plant> routePointList;
    @Inject
    protected RouteDetailViewModel routeDetailViewModel;
    private int currentPage = 0;
    View.OnClickListener listener;

    ViewPager viewPager;
    @Inject
    ViewPagerAdapter viewPagerAdapter;
    protected CompositeDisposable compositeDisposable;
    Context context;

    private ImageView leftArrow;
    private ImageView rightArrow;

    private ImageView mButton;

    /* map variables*/
    int x=0,y=0;
    Bitmap positionMarkerBitmap,canvasBitmap,mapBitmap, markerBitmap, colorMarkerBitmap;
    Canvas canvas;
    Resources resources;
    int width, height;
    private ImageView mapImage;
    //test
    List<LonLat> places = new ArrayList<>();
    ArrayList<PixelCoordinates> pointsPixelsCoordinates= new ArrayList();

    //arboretum
    public final static double MinLat = 50.708060;
    public final static double MaxLat = 50.713493;
    public final static double MinLon = 16.853841;
    public final static double MaxLon = 16.867359;
    /* /map variables*/


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRouteDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_route_detail);
        compositeDisposable = new CompositeDisposable();
        context = getApplicationContext();

        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle(R.string.toolbar_route_detail);

        TextView textView = findViewById(R.id.route_detail_description);
        textView.setMovementMethod(new ScrollingMovementMethod());

//        /*map*/
        fillPlantsCoordinates();
        mapImage = findViewById(R.id.route_detail_map);
        resources = getResources();
        //mapBitmap = BitmapFactory.decodeResource(resources, resources.getIdentifier("route_"+route_id, "drawable", getPackageName()));
        mapBitmap = BitmapFactory.decodeResource(resources, resources.getIdentifier("arboretum_map2", "drawable", getPackageName()));
        createBitmap();
        drawCanvas();
        drawMarkers(places, 0);
        /*drawing flowers*/
//        ArrayList<PixelCoordinates> pointsPixelsCoordinates= new ArrayList();
//        for(LonLat l: places){
//            pointsPixelsCoordinates.add(new PixelCoordinates(countX(l.lon), countY(l.lat)));
//        }
//        for(int i=0; i<pointsPixelsCoordinates.size(); i++){
//            if(i==0) {
//                canvas.drawBitmap(colorMarkerBitmap, pointsPixelsCoordinates.get(i).x, pointsPixelsCoordinates.get(i).y, null);
//            }else{
//                canvas.drawBitmap(markerBitmap, pointsPixelsCoordinates.get(i).x, pointsPixelsCoordinates.get(i).y, null);
//            }
//        }
//        for (PixelCoordinates p: pointsPixelsCoordinates) {
//            canvas.drawBitmap(markerBitmap, p.x, p.y, null);
//        }
        /* /drawing flowers*/

        mapImage.setImageBitmap(canvasBitmap);
//        /* /map*/


        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE);

        route_id = bundle.getInt(ROUTE_ID);


        /* beginning of route and routePointList usages */

        Disposable cdRoute = Maybe.fromCallable(() -> {
            return routeDetailViewModel.getById(route_id);
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(routeDb -> {
                            route = routeDb;
                            Log.i("mRoutes", String.valueOf(route_id));
                            binding.setRoute(route);
                        }
                        ,throwable -> {
                            /* onError() */
                            Toast.makeText(this, "Jakiś błąąąd w traskach... -.- -.-", Toast.LENGTH_LONG).show();
                        });

        compositeDisposable.add(cdRoute);


        Disposable cdPointsOnRoute = Maybe.fromCallable(() -> {
            return routeDetailViewModel.getPlantsOnRoute(route_id);
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(routePoints -> {
                            routePointList = routePoints;

                            leftArrow = findViewById(R.id.route_detail_left_arrow);
                            leftArrow.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(currentPage > 0 ) {
                                        viewPager.setCurrentItem(currentPage - 1);
                                    }
                                }
                            });

                            rightArrow = findViewById(R.id.route_detail_right_arrow);
                            rightArrow.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(currentPage < routePointList.size()) {
                                        viewPager.setCurrentItem(currentPage + 1);
                                    }
                                }
                            });

                            viewPager = findViewById(R.id.viewPager);
                            //viewPagerAdapter = new ViewPagerAdapter(context, listener);
                            viewPagerAdapter.setData(routePointList);
                            viewPager.setAdapter(viewPagerAdapter);



                            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                }

                                @Override
                                public void onPageSelected(int position) {
                                    currentPage = position;

                                    /* map */
                                    mapBitmap = BitmapFactory.decodeResource(resources, resources.getIdentifier("arboretum_map2", "drawable", getPackageName()));
                                    drawCanvas();
//                double lon=routePointList.get(position).getX();
//                double lat=routePointList.get(position).getY();
//                PixelCoordinates p=new PixelCoordinates(countX(lon), countY(lat));
//                int x=pointsPixelsCoordinates.get(position).x;
//                int y=pointsPixelsCoordinates.get(position).y;
//                canvas.drawBitmap(markerBitmap, p.x, p.y, null);
                                    drawMarkers(places, position);
                                    mapImage.setImageBitmap(canvasBitmap);
                                    /* /map */

                                    if(position == 0 && routePointList.size() > 1)
                                    {
                                        leftArrow.setImageDrawable(null);
                                    }
                                    else if(position == 1)
                                    {
                                        leftArrow.setImageResource(R.drawable.left_arrow);
                                    }

                                    if(position == routePointList.size()-1)
                                    {
                                        rightArrow.setImageDrawable(null);
                                    }
                                    else if(position == routePointList.size()-2)
                                    {
                                        rightArrow.setImageResource(R.drawable.right_arrow);
                                    }
                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {
                                }
                            });
                        }
                        ,throwable -> {
                            /* onError() */
                            Toast.makeText(this, "Jakiś błąąąd w traskach... -.- -.-", Toast.LENGTH_LONG);
                        });

        compositeDisposable.add(cdPointsOnRoute);


//        route = routeDetailViewModel.getRouteById(route_id);

//        if(route_id == 1)
//        {
//            routePointList = routeDetailViewModel.getRoutePointsForRoute1();
//        }
//        else
//        {
//            routePointList = routeDetailViewModel.getRoutePointsForRoute1();
//        }
//
//        leftArrow = findViewById(R.id.route_detail_left_arrow);
//        leftArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(currentPage > 0 ) {
//                    viewPager.setCurrentItem(currentPage - 1);
//                }
//            }
//        });
//
//        rightArrow = findViewById(R.id.route_detail_right_arrow);
//        rightArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(currentPage < routePointList.size()) {
//                    viewPager.setCurrentItem(currentPage + 1);
//                }
//            }
//        });
//
//        viewPager = findViewById(R.id.viewPager);
//        viewPagerAdapter = new ViewPagerAdapter(this, listener);
//        viewPagerAdapter.setData(routePointList);
//        viewPager.setAdapter(viewPagerAdapter);
//
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                currentPage = position;
//
//                /* map */
//                mapBitmap = BitmapFactory.decodeResource(resources, resources.getIdentifier("arboretum_map2", "drawable", getPackageName()));
//                drawCanvas();
////                double lon=routePointList.get(position).getX();
////                double lat=routePointList.get(position).getY();
////                PixelCoordinates p=new PixelCoordinates(countX(lon), countY(lat));
////                int x=pointsPixelsCoordinates.get(position).x;
////                int y=pointsPixelsCoordinates.get(position).y;
////                canvas.drawBitmap(markerBitmap, p.x, p.y, null);
//                drawMarkers(places, position);
//                mapImage.setImageBitmap(canvasBitmap);
//                /* /map */
//
//                if(position == 0 && routePointList.size() > 1)
//                {
//                    leftArrow.setImageDrawable(null);
//                }
//                else if(position == 1)
//                {
//                    leftArrow.setImageResource(R.drawable.left_arrow);
//                }
//
//                if(position == routePointList.size()-1)
//                {
//                    rightArrow.setImageDrawable(null);
//                }
//                else if(position == routePointList.size()-2)
//                {
//                    rightArrow.setImageResource(R.drawable.right_arrow);
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//            }
//        });

        Log.i("mRoutes", String.valueOf(route_id));
//        binding.setRoute(route);

        /* end of routePointList usages */


        mButton = findViewById(R.id.route_detail_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                Bundle navigation_bundle = new Bundle();
                navigation_bundle.putInt(ROUTE_ID, route_id);
                intent.putExtra(BUNDLE, navigation_bundle);
                startActivityForResult(intent, 123);
            }
        });

    }



    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    /* map */
    public void createBitmap(){
        positionMarkerBitmap = BitmapFactory.decodeResource(resources,R.drawable.ic_marker_black_big);
        markerBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_marker_flower);
        colorMarkerBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_marker_flower_color);
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

    public void drawMarker(int x, int y){
        canvas.drawBitmap(markerBitmap, x, y, null);
    }

    public void drawMarkers(List<LonLat> pointsCoordinates, int position){
        ArrayList<PixelCoordinates> pointsPixelsCoordinates= new ArrayList();
        for(LonLat l: pointsCoordinates){
            pointsPixelsCoordinates.add(new PixelCoordinates(countX(l.lon), countY(l.lat)));
        }
        for(int i=0; i<pointsPixelsCoordinates.size(); i++) {
            if (i == position) {
                canvas.drawBitmap(colorMarkerBitmap, pointsPixelsCoordinates.get(i).x, pointsPixelsCoordinates.get(i).y, null);
            } else {
                canvas.drawBitmap(markerBitmap, pointsPixelsCoordinates.get(i).x, pointsPixelsCoordinates.get(i).y, null);
            }
        }
    }
    /* /map */

    public void fillPlantsCoordinates(){
        places.add(new LonLat(16.856811,50.712181));
        places.add(new LonLat(16.858742,50.712460));
        places.add(new LonLat(16.861521,50.712269));
        places.add(new LonLat(16.863012,50.710591));
        //places.add(new LonLat(16.859096,50.710021));
        for(LonLat l: places){
            pointsPixelsCoordinates.add(new PixelCoordinates(countX(l.lon), countY(l.lat)));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Override
    public void onClick(View view) {
        int plantId = routePointList.get(viewPager.getCurrentItem()).getIdPlant();
        Toast.makeText(getApplicationContext(), "This page was clicked: " + plantId, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), PlantDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(PLANT_ID, plantId);
        intent.putExtra(BUNDLE, bundle);
        startActivityForResult(intent, 123);

    }
}
