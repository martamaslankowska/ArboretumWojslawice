package arboretum.arboretumwojslawice.View.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import arboretum.arboretumwojslawice.Commons.DividerItemDecoration;
import arboretum.arboretumwojslawice.Model.businessentity.Price;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.adapter.PriceForTicketsAdapter;
import arboretum.arboretumwojslawice.ViewModel.PriceViewModel;

public class PriceForTicketsFragment extends Fragment {

    private static final String KEY_LAYOUT_MANAGER = "fragment_price_for_tickets";
    protected RecyclerView mRecyclerView;

    protected LayoutManagerType mCurrentLayoutManagerType;
    PriceViewModel priceViewModel;

    PriceForTicketsAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<Price> mPrices;

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
        priceViewModel = new PriceViewModel();
        mPrices = priceViewModel.getPriceForMichal();


        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), 0));
        mAdapter = new PriceForTicketsAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setData(mPrices);

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
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }
}
