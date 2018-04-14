package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.PlantViewModel;
import arboretum.arboretumwojslawice.databinding.ActivityPlantDetailBinding;
import arboretum.arboretumwojslawice.databinding.ActivityRouteDetailBinding;

import static arboretum.arboretumwojslawice.View.activities.RouteDetailActivity.BUNDLE;

public class PlantDetailActivity extends AppCompatActivity {

    int plant_id;
    private Bundle bundle;
    public static final String BUNDLE = "BUNDLE";
    public static final String PLANT_ID = "PLANT_ID";
    public static final String TAB_ID = "TAB_ID";
    private Plant plant;
    private PlantViewModel plantViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPlantDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_plant_detail);

        plantViewModel = new PlantViewModel();

        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE);

        plant_id = bundle.getInt(PLANT_ID);
        plant = plantViewModel.getPlantById(plant_id);

        binding.setPlant(plant);
    }
}
