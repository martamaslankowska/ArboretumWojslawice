package arboretum.arboretumwojslawice.View.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.util.List;

import arboretum.arboretumwojslawice.Commons.DividerItemDecoration;
import arboretum.arboretumwojslawice.Model.businessentity.Route;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.adapter.RouteAdapter;
import arboretum.arboretumwojslawice.View.activities.RouteDetailActivity;
import arboretum.arboretumwojslawice.ViewModel.RouteViewModel;

/**
 * Created by weronika on 22.03.2018.
 */


public class RouteFragment extends Fragment  {

    private static final String KEY_LAYOUT_MANAGER = "fragment_route";
    private static final int SPAN_COUNT = 2;

    RouteViewModel routeViewModel;
    RouteAdapter.OnItemClickListener listener;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected RouteAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Route> mRoutes;
    protected ImageView route_map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_route, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.route_recycler_view);
        routeViewModel = new RouteViewModel();
        mRoutes = routeViewModel.getData();

        listener = new RouteAdapter.OnItemClickListener() {
            public void onItemClick(int route_id) {
                route_map = rootView.findViewById(R.id.route_map);
                route_map.setImageResource(mRoutes.get(route_id).getMapImage());
                Log.d("route_map", mRoutes.get(route_id).getMapString());
            }

            public void onDetailClick(int route_id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), RouteDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ROUTE_ID", route_id);
                intent.putExtra("BUNDLE", bundle);
                getActivity().startActivityForResult(intent, 123);
            }
        };

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getContext()));
        mAdapter = new RouteAdapter(listener);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setData(routeViewModel.getData());


        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        //mRecyclerView.addOnItemTouchListener(new RouteListListener(getContext(), mRecyclerView, listener));



        return rootView;
    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

}
