package arboretum.arboretumwojslawice.View.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.DividerItemDecoration;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.adapter.NewsAdapter;
import arboretum.arboretumwojslawice.ViewModel.NewsViewModel;
import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment {

//    protected RecyclerView mRecyclerView;
//    private static final String KEY_LAYOUT_MANAGER = "fragment_route";
//    protected LayoutManagerType mCurrentLayoutManagerType;

//    @Inject
//    protected NewsViewModel newsViewModel;
//    protected RecyclerView.LayoutManager mLayoutManager;

//    @Inject
//    protected NewsAdapter mAdapter;

//    private enum LayoutManagerType {
//        GRID_LAYOUT_MANAGER,
//        LINEAR_LAYOUT_MANAGER
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
//        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
//
//        mRecyclerView = rootView.findViewById(R.id.news_recycler_view);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), 0));
//        mRecyclerView.setAdapter(mAdapter);
//        mAdapter.setData(newsViewModel.getData());
//
//        mLayoutManager = new LinearLayoutManager(getActivity());
//
//        if (savedInstanceState != null) {
//            // Restore saved layout manager type.
//            mCurrentLayoutManagerType = (HomeFragment.LayoutManagerType) savedInstanceState
//                    .getSerializable(KEY_LAYOUT_MANAGER);
//        }
//
//        setRecyclerViewLayoutManager();
//
//        return rootView;
    }

//    public void setRecyclerViewLayoutManager() {
//        int scrollPosition = 0;
//
//        // If a layout manager has already been set, get current scroll position.
//        if (mRecyclerView.getLayoutManager() != null) {
//            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
//                    .findFirstCompletelyVisibleItemPosition();
//        }
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.scrollToPosition(scrollPosition);
//    }

//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        // Save currently selected layout manager.
//        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
//        super.onSaveInstanceState(savedInstanceState);
//    }
}
