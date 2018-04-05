package arboretum.arboretumwojslawice.View;

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

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private android.support.v7.widget.Toolbar mToolbar;
    FragmentManager mFragmentManager = getSupportFragmentManager();
    Fragment fragment = mFragmentManager.findFragmentById(R.id.fragment_container);
    FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
    HomeFragment mHomeFragment = new HomeFragment();
    RouteMapFragment mRouteMapFragment = new RouteMapFragment();
    FavouritesFragment mFavouritesFragment = new FavouritesFragment();
    MoreFragment mMoreFragment = new MoreFragment();
    int isExit = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mFragmentManager.popBackStackImmediate();
                    mHomeFragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container,mHomeFragment)
                            .addToBackStack(null)
                            .commit();
                    isExit = 0;
                    return true;
                case R.id.navigation_map_and_route:
                    mFragmentManager.popBackStackImmediate();
                    mRouteMapFragment = new RouteMapFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container,mRouteMapFragment)
                            .addToBackStack(null)
                            .commit();
                    isExit = 0;
                    return true;
                case R.id.navigation_favourites:
                    mFragmentManager.popBackStackImmediate();
                    mFavouritesFragment = new FavouritesFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container,mFavouritesFragment)
                            .addToBackStack(null)
                            .commit();
                    isExit = 0;
                    return true;
                case R.id.navigation_more:
                    mFragmentManager.popBackStackImmediate();
                    mMoreFragment = new MoreFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container,mMoreFragment)
                            .addToBackStack(null)
                            .commit();
                    isExit = 0;
                    return true;
            }
            return false;
        }
    };

    private BottomNavigationView.OnNavigationItemReselectedListener mOnNavigationItemReselectedListener
            = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {
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
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
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
            BottomNavigationView bottomNavigationView;
            bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
            mFragmentManager.popBackStackImmediate();
            mHomeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, mHomeFragment)
                    .addToBackStack(null)
                    .commit();
            isExit ++;
            if (isExit == 2) {
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.makeText(getApplicationContext(), R.string.toast_exit, Toast.LENGTH_SHORT).show();
            }

    }
}
