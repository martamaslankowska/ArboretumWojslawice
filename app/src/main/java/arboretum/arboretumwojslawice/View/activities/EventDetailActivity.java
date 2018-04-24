package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import arboretum.arboretumwojslawice.Model.businessentity.Event;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.EventViewModel;
import arboretum.arboretumwojslawice.ViewModel.PlantViewModel;
import arboretum.arboretumwojslawice.databinding.ActivityEventDetailBinding;
import arboretum.arboretumwojslawice.databinding.ActivityPlantDetailBinding;

public class EventDetailActivity extends AppCompatActivity {

    int event_id;
    private Bundle bundle;
    public static final String BUNDLE = "BUNDLE";
    public static final String EVENT_ID = "PLANT_ID";
    private Event mEvent;
    private EventViewModel eventViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEventDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        eventViewModel = new EventViewModel();

        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE);

        event_id = bundle.getInt(EVENT_ID);
        mEvent = eventViewModel.getEventById(event_id);

        getSupportActionBar().setTitle(mEvent.getType());

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
