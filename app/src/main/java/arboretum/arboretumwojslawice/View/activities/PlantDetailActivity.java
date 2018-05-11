package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.fragments.ListOfPlantsFragment;
import arboretum.arboretumwojslawice.ViewModel.PlantDetailViewModel;
import arboretum.arboretumwojslawice.databinding.ActivityPlantDetailBinding;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PlantDetailActivity extends DaggerAppCompatActivity {

    @Inject
    protected PlantDetailViewModel plantDetailViewModel;

    private int plant_id;
    private Bundle bundle;
    public static final String BUNDLE = "BUNDLE";
    public static final String PLANT_ID = "PLANT_ID";
    private ImageView mLocationMapButton;
    private CompositeDisposable compositeDisposable;

    private ImageView plantImage;
    private TextView floweringTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPlantDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_plant_detail);
        compositeDisposable = new CompositeDisposable();

        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE);

        plant_id = bundle.getInt(PLANT_ID);
        plantImage = findViewById(R.id.plant_detail_image);
        floweringTextView = findViewById(R.id.seassonTextView);


        Disposable cdPlant = Maybe.fromCallable(() -> {
            return plantDetailViewModel.getById(plant_id);
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dbPlant -> {
                            Plant plant = dbPlant;

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


                            Drawable d = getResources().getDrawable(plant.getImageId(getApplicationContext()));
                            int h = d.getIntrinsicHeight();
                            int w = d.getIntrinsicWidth();

                            DisplayMetrics displayMetrics = new DisplayMetrics();
                            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                            int width = displayMetrics.widthPixels;

                            if (h < 300 || w < 300) {
                                plantImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                plantImage.getLayoutParams().height = width/2;
                            }
                            else {
                                plantImage.getLayoutParams().height = width/2;
                                plantImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            }

                        }
                        ,throwable -> {
                            /* onError() */
                            Toast.makeText(this, "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG).show();
                        });

        compositeDisposable.add(cdPlant);

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
