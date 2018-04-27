package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.BottomNavigationViewHelper;
import arboretum.arboretumwojslawice.View.fragments.FavouritesFragment;
import arboretum.arboretumwojslawice.View.fragments.HomeFragment;
import arboretum.arboretumwojslawice.View.fragments.MoreFragment;
import arboretum.arboretumwojslawice.View.fragments.RouteMapFragment;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    private android.support.v7.widget.Toolbar mToolbar;
    FragmentManager mFragmentManager = getSupportFragmentManager();

    HomeFragment mHomeFragment = new HomeFragment();
    RouteMapFragment mRouteMapFragment = new RouteMapFragment();
    FavouritesFragment mFavouritesFragment = new FavouritesFragment();
    MoreFragment mMoreFragment = new MoreFragment();

    int isExit = 1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                mFragmentManager.popBackStackImmediate();
                mHomeFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,mHomeFragment)
                        .addToBackStack(null)
                        .commit();
                isExit = 1;
                Log.d("Arboretum", "Home" + String.valueOf(isExit));
                return true;
            case R.id.navigation_map_and_route:
                mFragmentManager.popBackStackImmediate();
                mRouteMapFragment = new RouteMapFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,mRouteMapFragment)
                        .addToBackStack(null)
                        .commit();
                isExit = 0;
                Log.d("Arboretum", String.valueOf(isExit));
                return true;
            case R.id.navigation_favourites:
                mFragmentManager.popBackStackImmediate();
                mFavouritesFragment = new FavouritesFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,mFavouritesFragment)
                        .addToBackStack(null)
                        .commit();
                isExit = 0;
                Log.d("Arboretum", String.valueOf(isExit));
                return true;
            case R.id.navigation_more:
                mFragmentManager.popBackStackImmediate();
                mMoreFragment = new MoreFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,mMoreFragment)
                        .addToBackStack(null)
                        .commit();
                isExit = 0;
                Log.d("Arboretum", String.valueOf(isExit));
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
        Log.d("Arboretum", String.valueOf(isExit));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
