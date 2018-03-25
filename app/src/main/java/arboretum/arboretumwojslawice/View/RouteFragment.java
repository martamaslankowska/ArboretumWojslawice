package arboretum.arboretumwojslawice.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.Route;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.RouteViewModel;

/**
 * Created by weronika on 22.03.2018.
 */

@SuppressLint("ValidFragment")
public class RouteFragment extends Fragment{

    private static final String KEY_LAYOUT_MANAGER = "route_fragment";
    private static final int SPAN_COUNT = 2;

    RouteViewModel routeViewModel;


    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Route> mRoutes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.route_fragment, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.route_recycler_view);
        routeViewModel = new RouteViewModel();

        mAdapter = new CustomAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setData(routeViewModel.getData());
        mRoutes = routeViewModel.getData();

        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        mRecyclerView.addOnItemTouchListener(new RouteListListener(getContext(), mRecyclerView, new RouteListListener.ClickListener() {
            @Override
            public void onClick(View view, int route_id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), RouteDetail.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ROUTE_ID", route_id);

                //bundle.putParcelable("M_LIST", Parcels.wrap(mRoutes));
                intent.putExtra("BUNDLE", bundle);
                getActivity().startActivityForResult(intent, 123);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

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
