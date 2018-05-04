package arboretum.arboretumwojslawice.View.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.DividerItemDecoration;
import arboretum.arboretumwojslawice.Model.businessentity.Price;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.adapter.PriceForServicesAdapter;
import arboretum.arboretumwojslawice.ViewModel.PriceForServicesViewModel;
import arboretum.arboretumwojslawice.ViewModel.PriceForTicketsViewModel;
import dagger.android.support.DaggerFragment;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PriceForServicesFragment extends DaggerFragment {

    private static final String KEY_LAYOUT_MANAGER = "fragment_price_for_services";
    protected RecyclerView mRecyclerView;

    protected LayoutManagerType mCurrentLayoutManagerType;

    @Inject
    protected PriceForServicesViewModel priceViewModel;
    @Inject
    protected PriceForServicesAdapter mAdapter;

    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Price> mPrices;
    protected CompositeDisposable compositeDisposable;


    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    public PriceForServicesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_price_for_services, container, false);

        mRecyclerView = rootView.findViewById(R.id.price_services_recycler_view);
        compositeDisposable = new CompositeDisposable();

        // getting data from database :)
        Disposable listOfPrices = Maybe.fromCallable(() -> {
            return priceViewModel.getAllPrices();
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(prices -> {
                            mPrices = prices;
                            mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), 0));
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.setData(mPrices);
                        }
                        ,throwable -> {
                            Toast.makeText(getActivity(), "We have error here...", Toast.LENGTH_LONG);
                        });

        compositeDisposable.add(listOfPrices);

        mLayoutManager = new LinearLayoutManager(getActivity());

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }

        setRecyclerViewLayoutManager();

        return rootView;
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
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

}
