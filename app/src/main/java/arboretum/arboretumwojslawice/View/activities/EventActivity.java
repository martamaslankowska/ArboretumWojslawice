package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.businessentity.Event;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.adapter.EventAdapter;
import arboretum.arboretumwojslawice.ViewModel.EventViewModel;
import dagger.android.support.DaggerAppCompatActivity;

public class EventActivity extends DaggerAppCompatActivity implements EventAdapter.OnItemClickListener {

    private static final String KEY_LAYOUT_MANAGER = "activity_event";
    public static final String EVENT_ID = "EVENT_ID";
    public static final String BUNDLE = "BUNDLE";

    @Inject
    protected EventViewModel eventViewModel;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(), EventDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(EVENT_ID, mEvents.get(position).getIdEvent());
        intent.putExtra(BUNDLE, bundle);
        startActivityForResult(intent, 123);
    }

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected EventActivity.LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;

    @Inject
    protected EventAdapter mAdapter;
    protected List<Event> mEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        /* toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_events_list);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        /* toolbar */

        mRecyclerView = findViewById(R.id.event_recycler_view);
        eventViewModel = new EventViewModel();
        mEvents = eventViewModel.getData();

        mAdapter.setData(mEvents);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setData(eventViewModel.getData());
        mLayoutManager = new LinearLayoutManager(this);
        setRecyclerViewLayoutManager();
    }

    public void setRecyclerViewLayoutManager() {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
