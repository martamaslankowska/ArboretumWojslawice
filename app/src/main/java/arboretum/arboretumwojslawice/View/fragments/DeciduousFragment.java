package arboretum.arboretumwojslawice.View.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.activities.ContactActivity;
import arboretum.arboretumwojslawice.View.activities.EventActivity;
import arboretum.arboretumwojslawice.View.activities.PlantActivity;
import arboretum.arboretumwojslawice.View.activities.PriceActivity;
import arboretum.arboretumwojslawice.View.activities.RulesActivity;
import arboretum.arboretumwojslawice.View.adapter.PlantAdapter;
import arboretum.arboretumwojslawice.ViewModel.PlantViewModel;

public class DeciduousFragment extends Fragment {

        private static final String KEY_LAYOUT_MANAGER = "fragment_deciduous";
        private static final int SPAN_COUNT = 2;

        private enum LayoutManagerType {
            GRID_LAYOUT_MANAGER,
            LINEAR_LAYOUT_MANAGER
        }
        PlantAdapter.OnItemClickListener listener;

        protected DeciduousFragment.LayoutManagerType mCurrentLayoutManagerType;

        protected RecyclerView mRecyclerView;
        protected PlantAdapter mAdapter;
        protected PlantViewModel mPlantViewModel;
        protected RecyclerView.LayoutManager mLayoutManager;
        protected List<Plant> mPlants;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_deciduous, container, false);

            mPlantViewModel = new PlantViewModel();
            mRecyclerView = (RecyclerView) view.findViewById(R.id.deciduous_recycler_view);

            listener = new PlantAdapter.OnItemClickListener() {
                public void onItemClick(int position) {
                    Toast.makeText(getContext(), "Pozycja nr " + (position + 1), Toast.LENGTH_SHORT).show();
                }
            };

            mPlants = mPlantViewModel.getData(0);
            mAdapter = new PlantAdapter(listener, mPlants);
            mRecyclerView.setAdapter(mAdapter);

            mLayoutManager = new LinearLayoutManager(getActivity());
            mCurrentLayoutManagerType = DeciduousFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;

            if (savedInstanceState != null) {
                // Restore saved layout manager type.
                mCurrentLayoutManagerType = (DeciduousFragment.LayoutManagerType) savedInstanceState
                        .getSerializable(KEY_LAYOUT_MANAGER);
            }
            setRecyclerViewLayoutManager();

            return view;
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