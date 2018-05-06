package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.DividerItemDecoration;
import arboretum.arboretumwojslawice.Model.businessentity.Event;
import arboretum.arboretumwojslawice.R;
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

    int event_id;
    private Bundle bundle;
    public static final String BUNDLE = "BUNDLE";
    public static final String EVENT_ID = "EVENT_ID";
    private Event mEvent;
    protected CompositeDisposable compositeDisposable;

    @Inject
    protected EventDetailViewModel eventDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEventDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE);

        event_id = bundle.getInt(EVENT_ID);

        Log.i("Id kwiatka: ", String.valueOf(event_id));

        compositeDisposable = new CompositeDisposable();

        Disposable d_event = Maybe.fromCallable(() -> {
            return eventDetailViewModel.getEventById(event_id);
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                            mEvent = event;
                        }
                        ,throwable -> {
                            Toast.makeText(this, "We have error here...", Toast.LENGTH_LONG);
                        });

        compositeDisposable.add(d_event);

        /* toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(mEvent.getType());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        /* /toolbar */

        binding.setEvent(mEvent);
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
}
