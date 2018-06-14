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
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ListOfPlantsFragment extends DaggerFragment implements PlantAdapter.OnItemClickListener{

    static final int REQUEST = 1;
    private static final String KEY_LAYOUT_MANAGER = "fragment_list_of_plants";
    public static final String PLANT_ID = "PLANT_ID";
    public static final String TAB_ID = "TAB_ID";
    public static final String BUNDLE = "BUNDLE";
    public static final String PLANT_POSITION = "plantPosition";


    protected ListOfPlantsFragment.LayoutManagerType currentLayoutManagerType;

    protected RecyclerView recyclerView;

    @Inject
    protected PlantAdapter adapter;

    @Inject
    protected PlantViewModel plantViewModel;
    protected RecyclerView.LayoutManager layoutManager;
    protected List<Plant> plants;
    protected CompositeDisposable compositeDisposable;
    private boolean isFavourite;
    private int n;



    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity().getApplicationContext(), PlantDetailActivity.class);

        this.getArguments().putInt(PLANT_POSITION, position);

        Bundle bundle = new Bundle();
        bundle.putInt(PLANT_ID, plants.get(position).getIdPlant());
        bundle.putInt(TAB_ID, n);
        intent.putExtra(BUNDLE, bundle);
        startActivityForResult(intent, REQUEST);
        recyclerView.scrollToPosition(position);
    }

    @Override
    public void onHeartClick(int position) {
        //Toast.makeText(getContext(), "Dodano do ulubionych; id: " + plants.get(position).getIdPlant(), Toast.LENGTH_SHORT).show();
        //plantViewModel.setFavourite(plants.get(position).getIdPlant());
        //mImageView.setImageResource(R.drawable.icons8_heart_red);


        Disposable listOfPlants = Completable.fromAction(() -> {
            isFavourite = plantViewModel.setFavourite(plants.get(position).getIdPlant());
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                            /* onSuccess() :) */
//                            int length = prices.size();
//                            try {
//                                Toast.makeText(getActivity(), "Było odwołanie do bazy i fajnie :)\nLiczba kwiatków w bazie: " + String.valueOf(length), Toast.LENGTH_SHORT).show();
//                            } catch (Exception e){
//                                Toast.makeText(getActivity(), "Ups, pusta baza :(", Toast.LENGTH_SHORT).show();
//                            }

                            if (isFavourite) {
                                Toast.makeText(getContext(), getString(R.string.toast_add_favourite_toast), Toast.LENGTH_SHORT).show();
                                plants.get(position).setFavourite(true);
//                                ImageView heart = recyclerView.getChildAt(position).findViewById(R.id.plant_heart);
//                                heart.setImageResource(R.drawable.ic_favourite_plant_fill);

                            }
                            else {
                                Toast.makeText(getContext(),  getString(R.string.toast_delete_favourite_toast), Toast.LENGTH_SHORT).show();
                                plants.get(position).setFavourite(false);
//                                ImageView heart = recyclerView.getChildAt(position).findViewById(R.id.plant_heart);
//                                heart.setImageResource(R.drawable.ic_favourite_plant_empty);
                            }
                            adapter.notifyItemChanged(position);
                        }
                        ,throwable -> {
                            /* onError() */
                            Toast.makeText(getActivity(), "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG);
                        });

        compositeDisposable.add(listOfPlants);

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

       // plantViewModel = new PlantViewModel();
        recyclerView = view.findViewById(R.id.conifeerous_recycler_view);
        compositeDisposable = new CompositeDisposable();

        Disposable listOfPlants = Maybe.fromCallable(() -> {
            return plantViewModel.getAllByKind(n);
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(plants -> {
                            this.plants = plants;
                            recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), 0));
                            adapter.setData(this.plants);
                            recyclerView.setAdapter(adapter);
                            layoutManager = new LinearLayoutManager(getActivity());
                            currentLayoutManagerType = ListOfPlantsFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                        }
                        ,throwable -> {
                            /* onError() */
                            Toast.makeText(getActivity(), "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG);
                        });

        compositeDisposable.add(listOfPlants);

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            currentLayoutManagerType = (ListOfPlantsFragment.LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager();

        return view;
    }

    public void setRecyclerViewLayoutManager() {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (recyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(recyclerView.getScrollState() == 0)
            recyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, currentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("Lista roslin", "WESZLO");

        if (requestCode == REQUEST) {
            if (resultCode == 1) {
                compositeDisposable = new CompositeDisposable();

                Disposable listOfPlants = Maybe.fromCallable(() -> {
                    return plantViewModel.getAllByKind(n);
                })
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(plants -> {
                                    this.plants = plants;
                                    adapter.setData(this.plants);
                                    recyclerView.setAdapter(adapter);


                                    adapter.notifyDataSetChanged();
                                }
                                , throwable -> {
                                    /* onError() */
                                    Toast.makeText(getActivity(), "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG);
                                });

                compositeDisposable.add(listOfPlants);
            }
        }

    }
}

