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

//    @Inject
//    PriceRepository priceRepository;
//    private PriceComponent priceComponent;

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

        /* COMPONENT - DAGGER */
//        priceComponent = DaggerPriceComponent.builder()
//                .applicationModule(new ApplicationModule(getApplication())).build();



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


        compositeDisposable = new CompositeDisposable();
        Disposable gettingPriceList2 = Maybe.fromCallable(() -> {
            return priceViewModel.getAllPrices();
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(prices -> {
                    String path = getDatabasePath("ArboretumDB").getAbsolutePath();
                            Toast.makeText(this, path, Toast.LENGTH_LONG).show();
//                            type1.setText(prices.get(0).getType());
                        }
                        ,throwable -> {
                            Toast.makeText(this, "Tu też błąd... -.- -.-", Toast.LENGTH_LONG).show();
                        });

        compositeDisposable.add(gettingPriceList2);
//
//
//
//
//        compositeDisposable = new CompositeDisposable();
//        Disposable gettingPriceList = Single.fromCallable(() -> {
//            return priceViewModel.getAllPrices();
//        })
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(prices -> {
//                    type1.setText(prices.get(0).getType());
//                        }
//                        ,throwable -> {
//                            Toast.makeText(this, "Aaaaaa, błąd... -.-", Toast.LENGTH_LONG).show();
//                });
//
//        compositeDisposable.add(gettingPriceList);


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
