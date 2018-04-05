package arboretum.arboretumwojslawice.View.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import arboretum.arboretumwojslawice.Model.MoreOptionItem;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.Adapter.MoreAdapter;
import arboretum.arboretumwojslawice.View.activities.ContactActivity;
import arboretum.arboretumwojslawice.View.activities.EventActivity;
import arboretum.arboretumwojslawice.View.activities.PlantActivity;
import arboretum.arboretumwojslawice.View.activities.PriceActivity;
import arboretum.arboretumwojslawice.View.activities.RulesActivity;
import arboretum.arboretumwojslawice.ViewModel.MoreViewModel;

public class MoreFragment extends Fragment {

    private static final String KEY_LAYOUT_MANAGER = "fragment_more";
    private static final int SPAN_COUNT = 2;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    MoreAdapter.OnItemClickListener listener;

    protected MoreFragment.LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected MoreAdapter mAdapter;
    protected MoreViewModel mMoreViewModel;
    protected RecyclerView.LayoutManager mLayoutManager;
    private List<MoreOptionItem> mOptions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        mMoreViewModel = new MoreViewModel(getContext());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.more_recycler_view);

        listener = new MoreAdapter.OnItemClickListener() {
            public void onItemClick(int position) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(getActivity().getApplicationContext(), PlantActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity().getApplicationContext(), EventActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(getActivity().getApplicationContext(), PriceActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(getActivity().getApplicationContext(), ContactActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(getActivity().getApplicationContext(), RulesActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    default:
                        Toast.makeText(getContext(), "Pozycja nr " + (position+1), Toast.LENGTH_SHORT).show();
                }

            }
        };

        mOptions = mMoreViewModel.getData();
        mAdapter = new MoreAdapter(listener, mOptions);
        mRecyclerView.setAdapter(mAdapter);


        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = MoreFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (MoreFragment.LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        return view;
    }

    public void setRecyclerViewLayoutManager(MoreFragment.LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = MoreFragment.LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = MoreFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = MoreFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
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
