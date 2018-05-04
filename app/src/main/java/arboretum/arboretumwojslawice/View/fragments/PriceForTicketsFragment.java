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
import arboretum.arboretumwojslawice.Model.DAO.HotelDao;
import arboretum.arboretumwojslawice.Model.businessentity.Price;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.adapter.PriceForTicketsAdapter;
import arboretum.arboretumwojslawice.ViewModel.PriceForTicketsViewModel;
import dagger.android.support.DaggerFragment;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PriceForTicketsFragment extends DaggerFragment {

    private static final String KEY_LAYOUT_MANAGER = "fragment_price_for_tickets";
    protected RecyclerView mRecyclerView;

    protected LayoutManagerType mCurrentLayoutManagerType;

    @Inject
    protected PriceForTicketsViewModel priceViewModel;

    @Inject
    protected PriceForTicketsAdapter mAdapter;

    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Price> mPrices;
    protected CompositeDisposable compositeDisposable;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    public PriceForTicketsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_price_for_tickets, container, false);

        mRecyclerView = rootView.findViewById(R.id.price_tickets_recycler_view);
        compositeDisposable = new CompositeDisposable();

        Disposable listOfPrices = Maybe.fromCallable(() -> {
            return priceViewModel.getAllPrices();
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(prices -> {
                            /* onSuccess() :) */
                            int length = prices.size();
                            try {
                                Toast.makeText(getActivity(), "Było odwołanie do bazy i fajnie\nLiczba cen w bazie biletowej: " + String.valueOf(length), Toast.LENGTH_SHORT).show();
                            } catch (Exception e){
                                Toast.makeText(getActivity(), "Ups, pusta baza :(", Toast.LENGTH_SHORT).show();
                            }

                            mPrices = prices;

                            mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), 0));
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.setData(mPrices);

                        }
                        ,throwable -> {
                            /* onError() */
                            Toast.makeText(getActivity(), "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG).show();
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
