package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.PlantDetailViewModel;
import arboretum.arboretumwojslawice.databinding.ActivityPlantDetailBinding;
import dagger.android.support.DaggerAppCompatActivity;

public class PlantDetailActivity extends DaggerAppCompatActivity {

    @Inject
    protected PlantDetailViewModel plantDetailViewModel;

    private int plant_id;
    private Bundle bundle;
    public static final String BUNDLE = "BUNDLE";
    public static final String PLANT_ID = "PLANT_ID";
    private Plant plant;
    private ImageView mLocationMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPlantDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_plant_detail);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE);

        plant_id = bundle.getInt(PLANT_ID);
        plant = plantDetailViewModel.getPlantById(plant_id);

        /* toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(plant.getName());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        /* /toolbar */

        mLocationMapButton = findViewById(R.id.plant_location_map_button);
        mLocationMapButton.setOnClickListener(view -> {
            Intent intent_location = new Intent(getApplicationContext(), PlantLocationMapActivity.class);
            Bundle bundle_location = new Bundle();
            bundle_location.putInt(PLANT_ID, plant_id);
            intent_location.putExtra(BUNDLE, bundle_location);
            startActivityForResult(intent_location, 123);
        });

        binding.setPlant(plant);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    public void getQRCode(View view) {
        Intent intent = new Intent(this, QRCodeActivity.class);
        startActivity(intent);
    }
}
