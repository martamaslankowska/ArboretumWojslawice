package arboretum.arboretumwojslawice.View.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.DividerItemDecoration;
import arboretum.arboretumwojslawice.Model.businessentity.Route;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.activities.RouteDetailActivity;
import arboretum.arboretumwojslawice.View.adapter.RouteAdapter;
import arboretum.arboretumwojslawice.ViewModel.RouteViewModel;
import arboretum.arboretumwojslawice.databinding.FragmentRouteBinding;
import dagger.android.support.DaggerFragment;

/**
 * Created by weronika on 22.03.2018.
 */

public class RouteFragment extends DaggerFragment implements RouteAdapter.OnItemClickListener {

    private static final String KEY_LAYOUT_MANAGER = "fragment_route";

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView mRecyclerView;
    View rootView;

    @Inject
    RouteViewModel routeViewModel;
    @Inject
    RouteAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Route> mRoutes;
    protected ImageView route_map;
    FragmentRouteBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_route, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.route_recycler_view);
        //routeViewModel = new RouteViewModel();
        mRoutes = routeViewModel.getData();

        binding = FragmentRouteBinding.inflate(inflater); //DataBindingUtil.inflate(inflater,R.layout.fragment_route,container,false);
       // binding.setRoute();

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), 1));
       // mAdapter = new RouteAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setData(mRoutes);
        mAdapter.setData(mRoutes);

        mLayoutManager = new LinearLayoutManager(getActivity());

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }

        setRecyclerViewLayoutManager();
        return rootView;
    }

    @Override
    public void onItemClick(int route_id) {
        route_map =  rootView.findViewById(R.id.route_map);
        route_map.setImageResource(mRoutes.get(route_id).getMapImage());

//        binding.setRoute(mRoutes.get(route_id));

        mAdapter.selectedPosition = route_id;
        mAdapter.notifyDataSetChanged();

        Log.d("route_map", mRoutes.get(route_id).getMapString());
    }

    @Override
    public void onDetailClick(int route_id) {
        Intent intent = new Intent(getActivity().getApplicationContext(), RouteDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ROUTE_ID", route_id);
        intent.putExtra("BUNDLE", bundle);
        getActivity().startActivityForResult(intent, 123);
    }

    public void setRecyclerViewLayoutManager() {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

}
