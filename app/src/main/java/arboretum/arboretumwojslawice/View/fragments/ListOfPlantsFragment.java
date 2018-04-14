package arboretum.arboretumwojslawice.View.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.activities.PlantDetailActivity;
import arboretum.arboretumwojslawice.View.adapter.PlantAdapter;
import arboretum.arboretumwojslawice.ViewModel.PlantViewModel;
import arboretum.arboretumwojslawice.databinding.PlantRowBinding;


public class ListOfPlantsFragment extends Fragment {


    private static final String KEY_LAYOUT_MANAGER = "fragment_list_of_plants";
    private static final int SPAN_COUNT = 2;
    public static final String PLANT_ID = "PLANT_ID";
    public static final String TAB_ID = "TAB_ID";
    public static final String BUNDLE = "BUNDLE";

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    PlantAdapter.OnItemClickListener listener;

    protected ListOfPlantsFragment.LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected PlantAdapter mAdapter;
    protected PlantViewModel mPlantViewModel;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Plant> mPlants;
    protected ImageView mImageView;

    private int n;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            n = bundle.getInt("NumberOfTab");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_of_plants, container, false);

        mPlantViewModel = new PlantViewModel();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.conifeerous_recycler_view);
//        View v = inflater.inflate(R.layout.plant_row, container, false);
        //mImageView = (ImageView) v.findViewById(R.id.plant_heart);

        listener = new PlantAdapter.OnItemClickListener() {
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "Pozycja nr " + (position + 1), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity().getApplicationContext(), PlantDetailActivity.class);
                Bundle bundle = new Bundle();                ;
                bundle.putInt(PLANT_ID, mPlants.get(position).getIdPlant());
                bundle.putInt(TAB_ID, n);
                intent.putExtra(BUNDLE, bundle);
                getActivity().startActivityForResult(intent, 123);
            }

            @Override
            public void onHeartClick(int position) {
                Toast.makeText(getContext(), "Dodano do ulubionych", Toast.LENGTH_SHORT).show();
                //mImageView.setImageResource(R.drawable.icons8_heart_red);
            }
        };
        switch(n) {
            case 0:
                mPlants = mPlantViewModel.getPlantsFromTab(1);
                break;
            case 1:
                mPlants = mPlantViewModel.getPlantsFromTab(2);
                break;
            case 2:
                mPlants = mPlantViewModel.getPlantsFromTab(3);
                break;
            case 3:
                mPlants = mPlantViewModel.getPlantsFromTab(4);
                break;
        }
        mAdapter = new PlantAdapter(listener, mPlants);
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = ListOfPlantsFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (ListOfPlantsFragment.LayoutManagerType) savedInstanceState
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

