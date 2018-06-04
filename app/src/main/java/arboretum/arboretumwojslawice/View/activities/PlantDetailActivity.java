package arboretum.arboretumwojslawice.View.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.Random;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.fragments.ListOfPlantsFragment;
import arboretum.arboretumwojslawice.ViewModel.PlantDetailViewModel;
import arboretum.arboretumwojslawice.databinding.ActivityPlantDetailBinding;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Completable;
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

    private ImageView plantImage, plantNoPhotoImage, favouriteImage;
    Context context;
    private boolean isFavourite;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPlantDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_plant_detail);
        compositeDisposable = new CompositeDisposable();
        context = getApplicationContext();

        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE);

        plant_id = bundle.getInt(PLANT_ID);
        plantImage = findViewById(R.id.plant_detail_image);
        mLocationMapButton = findViewById(R.id.plant_location_map_button);
        plantNoPhotoImage = findViewById(R.id.plantNoImageView);
        favouriteImage = findViewById(R.id.plant_favourite_button);


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

                            mLocationMapButton.setOnClickListener(view -> {
                                Intent intent_location = new Intent(getApplicationContext(), PlantLocationMapActivity.class);
                                Bundle bundle_location = new Bundle();
                                bundle_location.putInt(PLANT_ID, plant_id);
                                intent_location.putExtra(BUNDLE, bundle_location);
                                startActivityForResult(intent_location, 123);
                            });

                            binding.setPlant(plant);


//                            Drawable d = getResources().getDrawable(plant.getImageId(getApplicationContext()));
//                            int h = d.getIntrinsicHeight();
//                            int w = d.getIntrinsicWidth();

                            DisplayMetrics displayMetrics = new DisplayMetrics();
                            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                            int width = displayMetrics.widthPixels;

                            int minGifTime = 1500;
                            int maxGifTime = 3500;
                            int gifTime = minGifTime + (int)(Math.random()) * ((maxGifTime - minGifTime));

                            plantImage.getLayoutParams().height = width/2;
                            plantImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            plantImage.setImageResource(plant.getImageIdBig(context));

                            plantNoPhotoImage.getLayoutParams().height = width/2;
                            plantNoPhotoImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

                            if (plant.getImage() == null || plant.getImage().isEmpty()) { // no image
//                                noPlantTextView.setVisibility(View.GONE);
                                plantNoPhotoImage.setImageAlpha(200);
//                                plantImage.setColorFilter(Color.rgb(100, 100, 100), android.graphics.PorterDuff.Mode.MULTIPLY);
                            } else if (plant.getGif()) { // gif image
//                                noPlantTextView.setVisibility(View.GONE);
                                plantNoPhotoImage.setImageResource(R.drawable.weather_empty);

                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(plantNoPhotoImage, 1);
                                        Glide.with(context)
                                                .load(plant.getImageId(context))
                                                .into(imageViewTarget);
                                    }
                                }, gifTime);


                            } else { // normal image
//                                noPlantTextView.setVisibility(View.GONE);
                                plantNoPhotoImage.setVisibility(View.INVISIBLE);
                            }

                            mLocationMapButton.setImageResource(R.drawable.ic_map_plant_02);

                        }
                        ,throwable -> {
                            /* onError() */
                            Toast.makeText(this, "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG);
                        });

        compositeDisposable.add(cdPlant);


        favouriteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Disposable listOfPlants = Completable.fromAction(() -> {
                    isFavourite = plantDetailViewModel.setFavourite(plant_id);
                })
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> {
                                    if (isFavourite) {
                                        Toast.makeText(context,  getString(R.string.toast_add_favourite_toast), Toast.LENGTH_SHORT).show();
                                        favouriteImage.setImageResource(R.drawable.ic_favourite_plant_fill);
//                                      plants.get(position).setFavourite(true);
                                    }
                                    else {
                                        Toast.makeText(context,  getString(R.string.toast_delete_favourite_toast), Toast.LENGTH_SHORT).show();
                                        favouriteImage.setImageResource(R.drawable.ic_favourite_plant_empty);
//                                      plants.get(position).setFavourite(false);
                                    }
                                }
                                ,throwable -> {
                                    Toast.makeText(context, "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG);
                                });

                compositeDisposable.add(listOfPlants);
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

    public void getQRCode(View view) {
        Intent intent = new Intent(this, QRCodeActivity.class);
        startActivity(intent);
    }

    public int getImageId(Context c, String imageName) {
        return c.getResources().getIdentifier("arboretum.arboretumwojslawice:drawable/" + imageName, null, null);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(1, intent);
        finish();
    }
}
