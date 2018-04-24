package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.PlantViewModel;

public class PlantLocationMapActivity extends AppCompatActivity {

    public static final String BUNDLE = "BUNDLE";
    public static final String PLANT_ID = "PLANT_ID";
    private int plant_id;
    private PlantViewModel plantViewModel;
    private Bundle bundle;
    private Plant mPlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_location_map);

        plantViewModel = new PlantViewModel();

        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE);

        plant_id = bundle.getInt(PLANT_ID);
        mPlant = plantViewModel.getPlantById(plant_id); //do mPlant przypisujemy odpowiednią roślinkę

        /* toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_plant_location_map);
        getSupportActionBar().setSubtitle(mPlant.getName());

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        /* /toolbar */
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
