package arboretum.arboretumwojslawice.View.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.DividerItemDecoration;
import arboretum.arboretumwojslawice.Model.businessentity.Attraction;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.adapter.AttractionAdapter;
import arboretum.arboretumwojslawice.ViewModel.AttractionViewModel;
import dagger.android.support.DaggerFragment;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AttractionFragment extends DaggerFragment {

    @Inject
    AttractionViewModel attractionViewModel;

    private static final String KEY_LAYOUT_MANAGER = "fragment_attraction";

    protected AttractionFragment.LayoutManagerType currentLayoutManagerType;

    protected RecyclerView recyclerView;

    @Inject
    protected AttractionAdapter adapter;

    protected RecyclerView.LayoutManager layoutManager;
    protected List<Attraction> attractionsList;
    protected CompositeDisposable compositeDisposable;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    public AttractionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attraction, container, false);

        recyclerView = view.findViewById(R.id.attractionRecyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), 0));
        compositeDisposable = new CompositeDisposable();

        Disposable listOfAttractions = Maybe.fromCallable(() -> {
            return attractionViewModel.getAll();
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(attractions -> {
                            /* onSuccess() :) */
                            attractionsList = attractions;

                            recyclerView.setAdapter(adapter);
                            adapter.setData(attractionsList);
                            layoutManager = new LinearLayoutManager(getActivity());
                            currentLayoutManagerType = AttractionFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                        }
                        ,throwable -> {
                            /* onError() */
                            Toast.makeText(getActivity(), "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG);
                        });

        compositeDisposable.add(listOfAttractions);


        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            currentLayoutManagerType = (AttractionFragment.LayoutManagerType) savedInstanceState
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
