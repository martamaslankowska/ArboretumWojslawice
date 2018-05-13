package arboretum.arboretumwojslawice.View.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import arboretum.arboretumwojslawice.R;

public class RulesActivity extends AppCompatActivity {

    TextView title;
    TextView subtitle;
    TextView content1;
    TextView content2;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        /* toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_rules);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        /* /toolbar */

        //title = findViewById(R.id.rules_title);
        subtitle = findViewById(R.id.rules_subtitle);
        content1 = findViewById(R.id.rules_content1);
        content2 = findViewById(R.id.rules_content2);
        img = findViewById(R.id.rules_image);

        //title.setText(R.string.rules_title);
        subtitle.setText(R.string.rules_subtitle);
        content1.setText(R.string.rules_content_part1);
        //img.setBackgroundResource(R.drawable.rules_pictures);
        content2.setText(R.string.rules_content_part2);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
