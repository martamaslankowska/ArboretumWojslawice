package arboretum.arboretumwojslawice.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import arboretum.arboretumwojslawice.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private android.support.v7.widget.Toolbar mToolbar;
    FragmentManager fm = getSupportFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.fragment_container);

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(fragment == null) {
                        fragment = new HomeFragment();
                        fm.beginTransaction()
                                .add(R.id.fragment_container, fragment)
                                .commit();
                    }
                    return true;
                case R.id.navigation_map_and_route:
                    if(fragment == null) {
                        fragment = new RouteMapFragment();
                        fm.beginTransaction()
                                .add(R.id.fragment_container, fragment)
                                .commit();
                    }
                    return true;
                case R.id.navigation_favourites:
                    if(fragment == null) {
                        fragment = new FavouritesFragment();
                        fm.beginTransaction()
                                .add(R.id.fragment_container, fragment)
                                .commit();
                    }
                    return true;
                case R.id.navigation_more:
                    if(fragment == null) {
                        fragment = new MoreFragment();
                        fm.beginTransaction()
                                .add(R.id.fragment_container, fragment)
                                .commit();
                    }
                    return true;
            }
            return false;
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



    }

}
