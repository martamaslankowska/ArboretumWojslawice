package arboretum.arboretumwojslawice.View.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.DividerItemDecoration;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.activities.PlantDetailActivity;
import arboretum.arboretumwojslawice.View.adapter.FavouritesAdapter;
import arboretum.arboretumwojslawice.ViewModel.FavouriteViewModel;
import dagger.android.support.DaggerFragment;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class FavouritesFragment extends DaggerFragment implements FavouritesAdapter.OnItemClickListener {

    private static final String KEY_LAYOUT_MANAGER = "fragment_list_of_plants";
    public static final String PLANT_ID = "PLANT_ID";
    public static final String BUNDLE = "BUNDLE";

    protected FavouritesFragment.LayoutManagerType currentLayoutManagerType;

    protected RecyclerView recyclerView;
    private TextView noFavouritesTextView;
    FrameLayout background;

    @Inject
    protected FavouritesAdapter adapter;

    @Inject
    protected FavouriteViewModel favouriteViewModel;
    protected RecyclerView.LayoutManager layoutManager;
    protected List<Plant> plants;
    protected CompositeDisposable compositeDisposable;

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity().getApplicationContext(), PlantDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(PLANT_ID, plants.get(position).getIdPlant());
        intent.putExtra(BUNDLE, bundle);
        getActivity().startActivityForResult(intent, 123);
    }

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        recyclerView = view.findViewById(R.id.favourite_recycler_view);
        compositeDisposable = new CompositeDisposable();
        noFavouritesTextView = view.findViewById(R.id.noFavouriteTextView);
        background = view.findViewById(R.id.fragmentFavourites);

        Disposable listOfPlants = Maybe.fromCallable(() -> {
            return favouriteViewModel.getAllFavourites();
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(prices -> {

                            plants = prices;
                            recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), 0));
                            recyclerView.setAdapter(adapter);
                            adapter.setData(plants);
                            layoutManager = new LinearLayoutManager(getActivity());
                            currentLayoutManagerType = FavouritesFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                            if (!plants.isEmpty()) {
                                noFavouritesTextView.setVisibility(View.INVISIBLE);
                            } else {
                                noFavouritesTextView.setText(R.string.favourites_text);
                                background.setBackground(view.getResources().getDrawable(R.drawable.background40));

                            }
                        }
                        ,throwable -> {
                            /* onError() */
                            Toast.makeText(getActivity(), "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG);
                        });

        compositeDisposable.add(listOfPlants);


        //adapter = new PlantAdapter(listener);
//        recyclerView.setAdapter(adapter);
//        adapter.setData(plants);
//        layoutManager = new LinearLayoutManager(getActivity());
//        currentLayoutManagerType = ListOfPlantsFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            currentLayoutManagerType = (FavouritesFragment.LayoutManagerType) savedInstanceState
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
        recyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, currentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }


}
