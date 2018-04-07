package arboretum.arboretumwojslawice.View.activities;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.Stack;

import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.fragments.ListOfPlantsFragment;

public class PlantActivity extends AppCompatActivity {
    private static final String KEY_LAYOUT_MANAGER = "activity_plant";
    private static final int SPAN_COUNT = 2;

    private ListOfPlantsFragment mListOfPlantsFragment;
    private Stack<Integer> stos = new Stack<>();
    private TabLayout tabLayout;

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
        Bundle numOfTab = new Bundle();

        tabLayout = (TabLayout) findViewById(R.id.plant_tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {


            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(numOfTab != null) {
                    numOfTab.remove("NumberOfTab");
                }
                Log.d("Arboretum", "tab.getPosition() = " + String.valueOf(tab.getPosition()));
                mListOfPlantsFragment = new ListOfPlantsFragment();
                switch (tab.getPosition()) {
                    case 0:
                        numOfTab.putInt("NumberOfTab", 0);
                        mListOfPlantsFragment.setArguments(numOfTab);
                        stos.push(0);
                        break;
                    case 1:
                        numOfTab.putInt("NumberOfTab", 1);
                        mListOfPlantsFragment.setArguments(numOfTab);
                        stos.push(1);
                        break;
                    case 2:
                        numOfTab.putInt("NumberOfTab", 2);
                        mListOfPlantsFragment.setArguments(numOfTab);
                        stos.push(2);
                        break;
                    case 3:
                        numOfTab.putInt("NumberOfTab", 3);
                        mListOfPlantsFragment.setArguments(numOfTab);
                        stos.push(3);
                        break;
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.plant_fragment, mListOfPlantsFragment)
                        .addToBackStack(null)
                        .commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mListOfPlantsFragment = new ListOfPlantsFragment();
        numOfTab.putInt("NumberOfTab", 0);
        mListOfPlantsFragment.setArguments(numOfTab);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.plant_fragment,mListOfPlantsFragment)
                .addToBackStack(null)
                .commit();
        stos.push(0);

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

//    @Override
//    public void onBackPressed() {
//        int i = stos.pop();
//        switch (i) {
//            case 0:
//                mDeciduousFragment = new DeciduousFragment();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.plant_fragment,mDeciduousFragment)
//                        .addToBackStack(null)
//                        .commit();
//                break;
//            case 1:
//                mListOfPlantsFragment = new ListOfPlantsFragment();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.plant_fragment, mListOfPlantsFragment)
//                        .addToBackStack(null)
//                        .commit();
//                tabLayout.setTabMode(1);
//                break;
//            case 2:
//                mPerennialPlantsFragment = new PerennialPlantsFragment();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.plant_fragment,mPerennialPlantsFragment)
//                        .addToBackStack(null)
//                        .commit();
//                tabLayout.setTabMode(2);
//                break;
//            case 3:
//                mNationalCollectionFragment = new NationalCollectionFragment();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.plant_fragment,mNationalCollectionFragment)
//                        .addToBackStack(null)
//                        .commit();
//                tabLayout.setTabMode(3);
//                break;
//        }
//
//    }
}
