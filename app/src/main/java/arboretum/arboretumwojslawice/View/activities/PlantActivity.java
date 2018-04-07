package arboretum.arboretumwojslawice.View.activities;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.adapter.PlantAdapter;
import arboretum.arboretumwojslawice.View.fragments.ConiferousFragment;
import arboretum.arboretumwojslawice.View.fragments.DeciduousFragment;
import arboretum.arboretumwojslawice.View.fragments.MapFragment;
import arboretum.arboretumwojslawice.View.fragments.NationalCollectionFragment;
import arboretum.arboretumwojslawice.View.fragments.PerennialPlantsFragment;
import arboretum.arboretumwojslawice.View.fragments.RouteFragment;
import arboretum.arboretumwojslawice.ViewModel.PlantViewModel;

public class PlantActivity extends AppCompatActivity {
    private static final String KEY_LAYOUT_MANAGER = "activity_plant";
    private static final int SPAN_COUNT = 2;

    private ConiferousFragment mConiferousFragment;
    private DeciduousFragment mDeciduousFragment;
    private NationalCollectionFragment mNationalCollectionFragment;
    private PerennialPlantsFragment mPerennialPlantsFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle(R.string.toolbar_plants_list);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.plant_tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("Arboretum", "tab.getPosition() = " + String.valueOf(tab.getPosition()));
                switch (tab.getPosition()) {
                    case 0:
                        mDeciduousFragment = new DeciduousFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.plant_fragment,mDeciduousFragment)
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 1:
                        mConiferousFragment = new ConiferousFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.plant_fragment, mConiferousFragment)
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 2:
                        mPerennialPlantsFragment = new PerennialPlantsFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.plant_fragment,mPerennialPlantsFragment)
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 3:
                        mNationalCollectionFragment = new NationalCollectionFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.plant_fragment,mNationalCollectionFragment)
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

        mDeciduousFragment = new DeciduousFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.plant_fragment,mDeciduousFragment)
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
}
