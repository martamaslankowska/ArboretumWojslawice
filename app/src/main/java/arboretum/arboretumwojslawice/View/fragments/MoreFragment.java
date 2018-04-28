package arboretum.arboretumwojslawice.View.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import arboretum.arboretumwojslawice.Commons.DividerItemDecoration;
import arboretum.arboretumwojslawice.Model.MoreOptionItem;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.activities.NeighbourhoodActivity;
import arboretum.arboretumwojslawice.View.activities.OpeningHoursActivity;
import arboretum.arboretumwojslawice.View.activities.SettingsActivity;
import arboretum.arboretumwojslawice.View.adapter.MoreAdapter;
import arboretum.arboretumwojslawice.View.activities.ContactActivity;
import arboretum.arboretumwojslawice.View.activities.EventActivity;
import arboretum.arboretumwojslawice.View.activities.PlantActivity;
import arboretum.arboretumwojslawice.View.activities.PriceActivity;
import arboretum.arboretumwojslawice.View.activities.RulesActivity;
import arboretum.arboretumwojslawice.ViewModel.MoreViewModel;

public class MoreFragment extends Fragment {

    private static final String KEY_LAYOUT_MANAGER = "fragment_more";

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    MoreAdapter.OnItemClickListener listener;

    protected MoreFragment.LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected MoreAdapter mAdapter;
    protected MoreViewModel mMoreViewModel;
    protected RecyclerView.LayoutManager mLayoutManager;
    private List<MoreOptionItem> mOptions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        mMoreViewModel = new MoreViewModel(getContext());
        mRecyclerView = view.findViewById(R.id.more_recycler_view);

        listener = position -> {
            Intent intent;
            switch (position) {
                case 0: //spis roślin
                    intent = new Intent(getActivity().getApplicationContext(), PlantActivity.class);
                    getActivity().startActivity(intent);
                    break;
                case 1: //kalendarz imprez
                    intent = new Intent(getActivity().getApplicationContext(), EventActivity.class);
                    getActivity().startActivity(intent);
                    break;
                case 2: //cennik
                    intent = new Intent(getActivity().getApplicationContext(), PriceActivity.class);
                    getActivity().startActivity(intent);
                    break;
                case 3: //kontakt
                    intent = new Intent(getActivity().getApplicationContext(), ContactActivity.class);
                    getActivity().startActivity(intent);
                    break;
                case 4: //regulamin
                    intent = new Intent(getActivity().getApplicationContext(), RulesActivity.class);
                    getActivity().startActivity(intent);
                    break;
                case 5: //okoliczne
                    intent = new Intent(getActivity().getApplicationContext(), NeighbourhoodActivity.class);
                    getActivity().startActivity(intent);
                    break;
                case 6: //godziny otwarcia
                    intent = new Intent(getActivity().getApplicationContext(), OpeningHoursActivity.class);
                    getActivity().startActivity(intent);
                    break;
                case 7: //ustawienia
                    intent = new Intent(getActivity().getApplicationContext(), SettingsActivity.class);
                    getActivity().startActivity(intent);
                    break;
                default:
                    Toast.makeText(getContext(), "Pozycja nr " + (position+1), Toast.LENGTH_SHORT).show();
            }
        };

        mOptions = mMoreViewModel.getData();
        mAdapter = new MoreAdapter(listener, mOptions);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), 0));

        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = MoreFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (MoreFragment.LayoutManagerType) savedInstanceState
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
