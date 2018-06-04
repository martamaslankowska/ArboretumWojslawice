package arboretum.arboretumwojslawice.View.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.DividerItemDecoration;
import arboretum.arboretumwojslawice.Commons.DownloadFileFromURL;
import arboretum.arboretumwojslawice.Commons.Globals;
import arboretum.arboretumwojslawice.Commons.LocaleHelper;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.BottomNavigationViewHelper;
import arboretum.arboretumwojslawice.View.fragments.FavouritesFragment;
import arboretum.arboretumwojslawice.View.fragments.HomeFragment;
import arboretum.arboretumwojslawice.View.fragments.MoreFragment;
import arboretum.arboretumwojslawice.View.fragments.RouteMapFragment;
import arboretum.arboretumwojslawice.ViewModel.ContactViewModel;
import arboretum.arboretumwojslawice.ViewModel.MainViewModel;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static arboretum.arboretumwojslawice.View.activities.EventActivity.EVENT_DATE;
import static arboretum.arboretumwojslawice.View.fragments.ListOfPlantsFragment.BUNDLE;
import static arboretum.arboretumwojslawice.View.fragments.ListOfPlantsFragment.PLANT_ID;
import static arboretum.arboretumwojslawice.View.fragments.ListOfPlantsFragment.TAB_ID;


public class MainActivity extends DaggerAppCompatActivity {

    private android.support.v7.widget.Toolbar mToolbar;
    FragmentManager mFragmentManager = getSupportFragmentManager();

    HomeFragment mHomeFragment = new HomeFragment();
    RouteMapFragment mRouteMapFragment = new RouteMapFragment();
    FavouritesFragment mFavouritesFragment = new FavouritesFragment();
    MoreFragment mMoreFragment = new MoreFragment();
    SharedPreferences mPrefs;
    final String SYNC = "sync";

    final String URL = "http://arboretumdb.cba.pl/ArboretumDB.db";
    final String TAG = "Arboretum";

    int isExit = 1;


    protected List<Plant> favouritesPlants;
    protected CompositeDisposable compositeDisposable;
    protected CompositeDisposable compositeDisposable2;

    @Inject
    protected MainViewModel mainViewModel;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                //mFragmentManager.popBackStackImmediate();
                mHomeFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,mHomeFragment)
                        .addToBackStack(null)
                        .commit();
                isExit = 1;
                Log.d("Arboretum", "Home: " + String.valueOf(isExit));
                return true;
            case R.id.navigation_map_and_route:
                //mFragmentManager.popBackStackImmediate();
                mRouteMapFragment = new RouteMapFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,mRouteMapFragment)
                        .addToBackStack(null)
                        .commit();
                isExit = 0;
                Log.d("Arboretum", "MapRoute: " + String.valueOf(isExit));
                return true;
            case R.id.navigation_favourites:
                //mFragmentManager.popBackStackImmediate();
                mFavouritesFragment = new FavouritesFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,mFavouritesFragment)
                        .addToBackStack(null)
                        .commit();
                isExit = 0;
                Log.d("Arboretum", "Favourites: " + String.valueOf(isExit));
                return true;
            case R.id.navigation_more:
                //mFragmentManager.popBackStackImmediate();
                mMoreFragment = new MoreFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,mMoreFragment)
                        .addToBackStack(null)
                        .commit();
                isExit = 0;
                Log.d("Arboretum", "More: " + String.valueOf(isExit));
                return true;
        }
        return false;
    };

    private BottomNavigationView.OnNavigationItemReselectedListener mOnNavigationItemReselectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                break;
            case R.id.navigation_map_and_route:
                break;
            case R.id.navigation_favourites:
                break;
            case R.id.navigation_more:
                break;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setOnNavigationItemReselectedListener(mOnNavigationItemReselectedListener);

        mHomeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,mHomeFragment)
                .addToBackStack(null)
                .commit();
        isExit = 1;
        Log.d("Arboretum", "onCreate: " + String.valueOf(isExit));

        compositeDisposable = new CompositeDisposable();
        compositeDisposable2 = new CompositeDisposable();

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean isSync = mPrefs.getBoolean(SYNC, true);

        if(isSync) {
            Disposable listOfPlants = Maybe.fromCallable(() -> {
                return mainViewModel.getAllFavourites();
            })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((List<Plant> plant) -> {

                                favouritesPlants = plant;
                            /*POBIERANIE BAZY Z INTERNETU*/
                                if (Globals.isNetworkConnected(this) && Globals.isInternetOn(this)) {
                                    new DownloadFileFromURL().execute(URL);
                                    Log.d(TAG, "pobieram baze z internetu");
                                    while (!Globals.isDownload) {
                                    }
                                    Log.d(TAG, "pobrało się");
                                    Disposable fav = Completable.fromAction(() -> {
                                        mainViewModel.setAllFavourites(favouritesPlants);
                                    })
                                            .subscribeOn(Schedulers.computation())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(() -> {

                                                    }
                                                    , throwable -> {
                            /* onError() */
                                                        Toast.makeText(this, "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG);
                                                    });
                                    compositeDisposable.add(fav);
                                }
                            }
                            , throwable -> {
                            /* onError() */
                                Toast.makeText(this, "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG);
                            });

            compositeDisposable.add(listOfPlants);
        }

    }

    public void getQRCode(View view) {
        Intent intent = new Intent(this, QRCodeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if(isExit == 2) {
            finish();
            System.exit(0);
        }
        isExit ++;
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        mFragmentManager.popBackStackImmediate();
        mHomeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, mHomeFragment)
                .addToBackStack(null)
                .commit();
        if (isExit == 2) {
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.makeText(getApplicationContext(), R.string.toast_exit, Toast.LENGTH_SHORT).show();
        }
        Log.d("Arboretum", "onBackPressed: " + String.valueOf(isExit));
    }

    @Override
    protected void onStart() {
        Log.d("Arboretum", "onStart: " + String.valueOf(isExit));
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("Arboretum", "onStop: " + String.valueOf(isExit));
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("Arboretum", "onDestroy: " + String.valueOf(isExit));
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d("Arboretum", "onRestart: " + String.valueOf(isExit));
        super.onRestart();
    }

    @Override
    protected void onResume() {
        if (isExit == 2) {
            isExit = 1;
        }
        Log.d("Arboretum", "onResume: " + String.valueOf(isExit));
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("Arboretum", "onPause: " + String.valueOf(isExit));
        super.onPause();
    }

    public void newsClick(View view) {
        Intent intent = new Intent(this, NewsDetailActivity.class);
        startActivity(intent);
    }

    public void eventClick(View view) {
        Intent intent = new Intent(getApplicationContext(), EventDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(EVENT_DATE, Globals.nearestEvents.getEventDateInteger());
        intent.putExtra(BUNDLE, bundle);
        startActivityForResult(intent, 123);
    }

    public void plantClick(View view) {
        Intent intent = new Intent(getApplicationContext(), PlantDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(PLANT_ID, Globals.seasonPlant.getIdPlant());
        bundle.putInt(TAB_ID, Globals.seasonPlant.getKind());
        intent.putExtra(BUNDLE, bundle);
        startActivityForResult(intent, 123);
    }


}
