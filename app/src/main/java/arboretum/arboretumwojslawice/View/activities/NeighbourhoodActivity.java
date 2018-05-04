package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.fragments.AttractionFragment;
import arboretum.arboretumwojslawice.View.fragments.HotelFragment;
import arboretum.arboretumwojslawice.View.fragments.PriceForServicesFragment;
import arboretum.arboretumwojslawice.View.fragments.PriceForTicketsFragment;
import arboretum.arboretumwojslawice.View.fragments.RestaurantFragment;
import dagger.android.support.DaggerAppCompatActivity;

public class NeighbourhoodActivity extends DaggerAppCompatActivity {

    private AttractionFragment mAttractionFragment;
    private HotelFragment mHotelFragment;
    private RestaurantFragment mRestaurantFragment;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbourhood);

        /* toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle(R.string.toolbar_neighbourhood);
        /* /toolbar */

        tabLayout = findViewById(R.id.neighbourhood_tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        mAttractionFragment = new AttractionFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.neighbourhood_fragment, mAttractionFragment)
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 1:
                        mRestaurantFragment = new RestaurantFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.neighbourhood_fragment, mRestaurantFragment)
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 2:
                        mHotelFragment = new HotelFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.neighbourhood_fragment, mHotelFragment)
                                .addToBackStack(null)
                                .commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mAttractionFragment = new AttractionFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.neighbourhood_fragment, mAttractionFragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        super.onSaveInstanceState(savedInstanceState);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void getQRCode(View view) {
        Intent intent = new Intent(this, QRCodeActivity.class);
        startActivity(intent);
    }
}
