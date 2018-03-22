package arboretum.arboretumwojslawice.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import arboretum.arboretumwojslawice.R;

public class RouteMapFragment extends Fragment {

    private ViewPager mViewPager;
    private SectionsPageAdapter mSectionsPageAdapter;
    private static final String TAG = "MainActivity";
    FragmentManager fm ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_route_map, container, false);

        //mSectionsPageAdapter = new SectionsPageAdapter(fm);
        mViewPager = (ViewPager) v.findViewById(R.id.container);
        //setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        return v;
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(fm);
        adapter.addFragment(new MapFragment(), "MAP");
        adapter.addFragment(new RouteFragment(), "ROUTE");
        viewPager.setAdapter(adapter);
    }

}
