package arboretum.arboretumwojslawice.View.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.components.PriceComponent;
import arboretum.arboretumwojslawice.Model.Repository.PriceRepository;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.PriceViewModel;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PriceActivity extends AppCompatActivity {

    TextView type1;
    TextView price1;
    TextView description1;
    TextView type2;
    TextView price2;
    TextView description2;

    CompositeDisposable compositeDisposable;
    PriceViewModel priceViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_price);

        priceViewModel = new PriceViewModel(this.getApplication());

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


        compositeDisposable = new CompositeDisposable();

        /* GETTING DATA FROM DATABASE - in RXJava (it's not so hard ;)) */
        Disposable gettingPriceList2 = Maybe.fromCallable(() -> {
            return priceViewModel.getAllPrices();
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(prices -> {
                    /* onSuccess() :) Here we do whatever :)
                    * For example here we do the DataBinding or RecyclerView stuff */
                    String length = Integer.toString(prices.size());
                    Toast.makeText(this, length, Toast.LENGTH_LONG).show();
                    type1.setText(prices.get(0).getType());
                    description1.setText(prices.get(0).getDescription());
                    price1.setText(Integer.toString((prices.get(0).getAmount()).intValue()) + " zł");
                    type2.setText(prices.get(1).getType());
                    description2.setText(prices.get(1).getDescription());
                    price2.setText(Integer.toString((prices.get(1).getAmount()).intValue()) + " zł");
                }
                ,throwable -> {
                    /* onError() - here we are sad... :( */
                    Toast.makeText(this, "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG).show();
                });


        compositeDisposable.add(gettingPriceList2);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        compositeDisposable.clear();
    }
}
