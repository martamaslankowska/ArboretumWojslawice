package arboretum.arboretumwojslawice.View.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import arboretum.arboretumwojslawice.R;

public class PriceActivity extends AppCompatActivity {

    TextView type1;
    TextView price1;
    TextView description1;
    TextView type2;
    TextView price2;
    TextView description2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_price);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        type1 = findViewById(R.id.type1);
        price1 = findViewById(R.id.price1);
        description1 = findViewById(R.id.description1);
        type2 = findViewById(R.id.type2);
        price2 = findViewById(R.id.price2);
        description2 = findViewById(R.id.description2);

        price1.setText("5,- zł");

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
