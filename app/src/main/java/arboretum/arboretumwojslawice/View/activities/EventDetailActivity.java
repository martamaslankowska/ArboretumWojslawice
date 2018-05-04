package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.businessentity.Event;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.EventDetailViewModel;
import arboretum.arboretumwojslawice.ViewModel.EventViewModel;
import arboretum.arboretumwojslawice.databinding.ActivityEventDetailBinding;
import dagger.android.support.DaggerAppCompatActivity;

public class EventDetailActivity extends DaggerAppCompatActivity {

    int event_id;
    private Bundle bundle;
    public static final String BUNDLE = "BUNDLE";
    public static final String EVENT_ID = "EVENT_ID";
    private Event mEvent;

    @Inject
    protected EventDetailViewModel eventDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEventDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE);

        event_id = bundle.getInt(EVENT_ID);
        mEvent = eventDetailViewModel.getEventById(event_id);

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
