package arboretum.arboretumwojslawice.View.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import arboretum.arboretumwojslawice.R;

public class OpeningHoursActivity extends AppCompatActivity {

    ImageView gifImageView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_hours);
        context = getApplicationContext();

        /* toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_opening_hours);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        /* /toolbar */


//        gifImageView = findViewById(R.id.gifImageView);
//
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                // load and run gif for one time only
//                GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(gifImageView, 1);
//                Glide.with(context)
//                        .load(R.drawable.bird_test)
//                        .into(imageViewTarget);
//
//            }
//        }, 1500); // 1.5 second delay

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
