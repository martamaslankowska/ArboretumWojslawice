package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.DividerItemDecoration;
import arboretum.arboretumwojslawice.Model.additionalEntity.EventRowList;
import arboretum.arboretumwojslawice.Model.businessentity.Event;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.adapter.EventDetailAdapter;
import arboretum.arboretumwojslawice.ViewModel.EventDetailViewModel;
import arboretum.arboretumwojslawice.ViewModel.EventViewModel;
import arboretum.arboretumwojslawice.databinding.ActivityEventDetailBinding;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EventDetailActivity extends DaggerAppCompatActivity {

    int event_date;
    private Bundle bundle;
    public static final String BUNDLE = "BUNDLE";
    public static final String EVENT_DATE = "EVENT_DATE";
    private List<Event> mEventList;
    EventRowList eventRowList;

    protected RecyclerView mRecyclerView;
    TextView eventsNames;

    @Inject
    protected EventDetailAdapter mAdapter;
    protected CompositeDisposable compositeDisposable;

    @Inject
    protected EventDetailViewModel eventDetailViewModel;
    protected RecyclerView.LayoutManager mLayoutManager;

    protected EventDetailActivity.LayoutManagerType mCurrentLayoutManagerType;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_event_detail);
        ActivityEventDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail);
        eventsNames = findViewById(R.id.event_detail_names);


        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE);
        event_date = bundle.getInt(EVENT_DATE);

        mRecyclerView = findViewById(R.id.event_detail_recycler_view);

       // Log.i("Id kwiatka: ", String.valueOf(event_id));

        compositeDisposable = new CompositeDisposable();

        Disposable d_event = Maybe.fromCallable(() -> {
            return eventDetailViewModel.getAllDuringGivenDate(event_date);
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                            mEventList = event;
                            Log.d("Liczba wydarzeń: ", String.valueOf(mEventList.size()));
                            mRecyclerView.addItemDecoration(new DividerItemDecoration(this, 0));
                            mAdapter.setData(mEventList);
                            mRecyclerView.setAdapter(mAdapter);
                            /* toolbar */
                            Toolbar toolbar = findViewById(R.id.toolbar_back);
                            setSupportActionBar(toolbar);
                            getSupportActionBar().setTitle(getString(R.string.toolbar_event_detail) + " " + dateIntString(event_date));

                            if (getSupportActionBar() != null) {
                                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                                getSupportActionBar().setDisplayShowHomeEnabled(true);
                            }
                        }
                        ,throwable -> {
                            Toast.makeText(this, "We have error here...", Toast.LENGTH_LONG);
                        });

        compositeDisposable.add(d_event);



        Disposable d_event_names = Maybe.fromCallable(() -> {
            return eventDetailViewModel.getEventRowList(event_date);
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(events -> {
                            eventRowList = events;
                            binding.setEvent(eventRowList);

                            int stringNumber = (int) (Math.random() * 3) + 1;  // returns numbers from 1 to n

                            String stringName = "calendar_0" + String.valueOf(stringNumber);
                            String eventTitle = getStringResourceByName(stringName);

                            eventsNames.setText(eventTitle.toUpperCase());
                            int nrOfLines = eventsNames.getLineCount();
                            if (nrOfLines < 3)
                                eventsNames.setPadding(0,16,0,0);
                            else if (nrOfLines < 4)
                                eventsNames.setPadding(0,8,0,0);
                        }
                        ,throwable -> {
                            Toast.makeText(this, "We have error here...", Toast.LENGTH_LONG);
                        });

        compositeDisposable.add(d_event_names);

        //binding.eventDetailDate.setText(eventDetailViewModel.getDateString(event_date));

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

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    public void getQRCode(View view) {
        Intent intent = new Intent(this, QRCodeActivity.class);
        startActivity(intent);
    }

    public String dateIntString(int date) {
        Integer day = date - ((date/100)*100);
        Integer month = (date/100) - ((date/10000)*100);
        Integer year = date/10000;

        return (day<10 ? "0" : "") + String.valueOf(day) + "." + (month<10 ? "0" : "") + String.valueOf(month);
    }


    private String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }


}
