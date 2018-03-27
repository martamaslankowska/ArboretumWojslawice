package arboretum.arboretumwojslawice.View;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import arboretum.arboretumwojslawice.R;

public class RouteMapFragment extends Fragment {

    private FrameLayout mFrameLayout;
    private MapFragment mMapFragment;
    private RouteFragment mRouteFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_route_map, container, false);
        Log.d("Arboretum", "mRouteMapFragment");
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        mMapFragment = new MapFragment();
                        getChildFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container,mMapFragment)
                                .addToBackStack(null)
                                .commit();
                        Log.d("Arboretum", "mMapFragment");
                        break;
                    case 1:
                        mRouteFragment = new RouteFragment();
                        getChildFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container,mRouteFragment)
                                .addToBackStack(null)
                                .commit();
                        Log.d("Arboretum", "mRouteFragment");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Fragment mapFragment = new MapFragment();
        FragmentTransaction transaction = getChildFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.fragment_container, mapFragment).commit();
    }



}
