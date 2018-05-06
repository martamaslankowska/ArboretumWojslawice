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

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.DividerItemDecoration;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.activities.PlantDetailActivity;
import arboretum.arboretumwojslawice.View.adapter.PlantAdapter;
import arboretum.arboretumwojslawice.ViewModel.PlantViewModel;
import dagger.android.support.DaggerFragment;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ListOfPlantsFragment extends DaggerFragment implements PlantAdapter.OnItemClickListener{

    private static final String KEY_LAYOUT_MANAGER = "fragment_list_of_plants";
    public static final String PLANT_ID = "PLANT_ID";
    public static final String TAB_ID = "TAB_ID";
    public static final String BUNDLE = "BUNDLE";

    protected ListOfPlantsFragment.LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;

    @Inject
    protected PlantAdapter mAdapter;

    @Inject
    protected PlantViewModel mPlantViewModel;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Plant> mPlants;
    protected CompositeDisposable compositeDisposable;

    private int n;

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity().getApplicationContext(), PlantDetailActivity.class);
        Bundle bundle = new Bundle();
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

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

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

       // mPlantViewModel = new PlantViewModel();
        mRecyclerView = view.findViewById(R.id.conifeerous_recycler_view);
        compositeDisposable = new CompositeDisposable();


//        switch(n) {
//            case 0:
//                mPlants = mPlantViewModel.getPlantsFromTab(1);
//                break;
//            case 1:
//                mPlants = mPlantViewModel.getPlantsFromTab(2);
//                break;
//            case 2:
//                mPlants = mPlantViewModel.getPlantsFromTab(3);
//                break;
//            case 3:
//                mPlants = mPlantViewModel.getPlantsFromTab(4);
//                break;
//        }


        Disposable listOfPlants = Maybe.fromCallable(() -> {
            return mPlantViewModel.getAllByKind(n);
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(prices -> {
                            /* onSuccess() :) */
//                            int length = prices.size();
//                            try {
//                                Toast.makeText(getActivity(), "Było odwołanie do bazy i fajnie :)\nLiczba kwiatków w bazie: " + String.valueOf(length), Toast.LENGTH_SHORT).show();
//                            } catch (Exception e){
//                                Toast.makeText(getActivity(), "Ups, pusta baza :(", Toast.LENGTH_SHORT).show();
//                            }

                            mPlants = prices;

                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.setData(mPlants);
                            mLayoutManager = new LinearLayoutManager(getActivity());
                            mCurrentLayoutManagerType = ListOfPlantsFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;

                        }
                        ,throwable -> {
                            /* onError() */
                            Toast.makeText(getActivity(), "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG);
                        });

        compositeDisposable.add(listOfPlants);



        //mAdapter = new PlantAdapter(listener);
//        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.setData(mPlants);
//        mLayoutManager = new LinearLayoutManager(getActivity());
//        mCurrentLayoutManagerType = ListOfPlantsFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;

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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }

}

