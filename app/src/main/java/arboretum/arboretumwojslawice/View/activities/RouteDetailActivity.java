package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.Model.businessentity.Route;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.adapter.ViewPagerAdapter;
import arboretum.arboretumwojslawice.ViewModel.RouteDetailViewModel;
import arboretum.arboretumwojslawice.databinding.ActivityRouteDetailBinding;

public class RouteDetailActivity extends AppCompatActivity {

    public static final String BUNDLE = "BUNDLE";
    public static final String ROUTE_ID = "ROUTE_ID";
    private Bundle bundle;
    private int route_id;
    private Route route;
    private List<Plant> plantList;
    RouteDetailViewModel routeDetailViewModel;
    private int currentPage = 0;

    ViewPager viewPager;

    private ImageView leftArrow;
    private ImageView rightArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRouteDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_route_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle(R.string.toolbar_route_detail);
        routeDetailViewModel = new RouteDetailViewModel();

        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE);

        route_id = bundle.getInt(ROUTE_ID);
        route = routeDetailViewModel.getRouteById(route_id);


        plantList = routeDetailViewModel.getPlants();

        leftArrow = findViewById(R.id.route_detail_left_arrow);
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPage > 0) {
                    viewPager.setCurrentItem(currentPage - 1);
                }
            }
        });

        rightArrow = findViewById(R.id.route_detail_right_arrow);
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPage < plantList.size()) {
                    viewPager.setCurrentItem(currentPage + 1);
                }
            }
        });

        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, plantList);
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(getApplicationContext(), "Kwiatek " + (position+1), Toast.LENGTH_SHORT).show();
                currentPage = position;

                if(position == 0)
                {
                    leftArrow.setImageDrawable(null);
                }
                else if(position == 1)
                {
                    leftArrow.setImageResource(R.drawable.left_arrow);
                }

                if(position == plantList.size()-1)
                {
                    rightArrow.setImageDrawable(null);
                }
                else if(position == 1)
                {
                    rightArrow.setImageResource(R.drawable.right_arrow);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
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
}
