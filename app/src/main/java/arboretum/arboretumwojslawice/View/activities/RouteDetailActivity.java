package arboretum.arboretumwojslawice.View.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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

import arboretum.arboretumwojslawice.Commons.map.LonLat;
import arboretum.arboretumwojslawice.Commons.map.PixelCoordinates;
import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Location;
import arboretum.arboretumwojslawice.Model.businessentity.PointOnRoute;
import arboretum.arboretumwojslawice.Model.businessentity.Route;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.adapter.ViewPagerAdapter;
import arboretum.arboretumwojslawice.ViewModel.RouteDetailViewModel;
import arboretum.arboretumwojslawice.databinding.ActivityRouteDetailBinding;

public class RouteDetailActivity extends AppCompatActivity {

    public static final String BUNDLE = "BUNDLE";
    public static final String ROUTE_ID = "ROUTE_ID";
    public static final String PLANT_ID = "PLANT_ID";
    public static final String TAB_ID = "TAB_ID";

    private Bundle bundle;
    private int route_id;
    private Route route;
    private List<PointOnRoute> routePointList;
    RouteDetailViewModel routeDetailViewModel;
    private int currentPage = 0;
    View.OnClickListener listener;

    ViewPager viewPager;

    private ImageView leftArrow;
    private ImageView rightArrow;

    private ImageView mButton;

    /* map variables*/
    int x=0,y=0;
    Bitmap positionMarkerBitmap,canvasBitmap,mapBitmap, markerBitmap;
    Canvas canvas;
    Resources resources;
    int width, height;
    private ImageView mapImage;

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

        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle(R.string.toolbar_route_detail);
        routeDetailViewModel = new RouteDetailViewModel();

        TextView textView = findViewById(R.id.route_detail_description);
        textView.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE);

        /*map*/
        mapImage = findViewById(R.id.route_detail_map);
        resources = getResources();
        CreateBitmap();
        DrawCanvas();
        mapImage.setImageBitmap(canvasBitmap);
        /* /map*/

        route_id = bundle.getInt(ROUTE_ID);
        route = routeDetailViewModel.getRouteById(route_id);

        if(route_id == 1)
        {
            routePointList = routeDetailViewModel.getRoutePointsForRoute1();
        }
        else
        {
            routePointList = routeDetailViewModel.getRoutePointsForRoute1();
        }

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

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int plantId = routePointList.get(viewPager.getCurrentItem()).getPlant().getIdPlant();
                Toast.makeText(getApplicationContext(), "This page was clicked: " + plantId, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), PlantDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(PLANT_ID, plantId);
                intent.putExtra(BUNDLE, bundle);
                startActivityForResult(intent, 123);
            }
        };

        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, routePointList, listener);
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
//                Toast.makeText(getApplicationContext(), "Kwiatek " + (position+1), Toast.LENGTH_SHORT).show();
                currentPage = position;

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

        Log.i("mRoutes", String.valueOf(route_id));
        binding.setRoute(route);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    /* map */
    public void CreateBitmap(){
        positionMarkerBitmap = BitmapFactory.decodeResource(resources,R.drawable.ic_marker_black_big);
        mapBitmap = BitmapFactory.decodeResource(resources,R.drawable.arboretum_map2);
        markerBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_marker_flower);
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

    public void drawMarkers(List<LonLat> pointsCoordinates){
        ArrayList<PixelCoordinates> pointsPixelsCoordinates= new ArrayList();
        for(LonLat l: pointsCoordinates){
            pointsPixelsCoordinates.add(new PixelCoordinates(countX(l.lon), countY(l.lat)));
        }
        for (PixelCoordinates p: pointsPixelsCoordinates) {
            canvas.drawBitmap(markerBitmap, p.x, p.y, null);
        }
    }
    /* /map */
}
