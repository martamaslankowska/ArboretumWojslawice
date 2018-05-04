package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.fragments.PriceForServicesFragment;
import arboretum.arboretumwojslawice.View.fragments.PriceForTicketsFragment;
import dagger.android.support.DaggerAppCompatActivity;

public class PriceActivity extends DaggerAppCompatActivity {

    protected PriceForServicesFragment mPriceForServicesFragment;
    protected PriceForTicketsFragment mPriceForTicketsFragment;
    private TabLayout tabLayout;
//    private PriceViewModel priceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);

        /* toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle(R.string.toolbar_price);
        /* /toolbar */


//        Toast.makeText(this, "PriceViewModel:\n" + priceViewModel.toString(), Toast.LENGTH_LONG).show();


        tabLayout = findViewById(R.id.price_tabs);



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        mPriceForTicketsFragment = new PriceForTicketsFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.price_fragment, mPriceForTicketsFragment)
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 1:
                        mPriceForServicesFragment = new PriceForServicesFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.price_fragment, mPriceForServicesFragment)
                                .addToBackStack(null)
                                .commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mPriceForTicketsFragment = new PriceForTicketsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.price_fragment, mPriceForTicketsFragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        super.onSaveInstanceState(savedInstanceState);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void getQRCode(View view) {
        Intent intent = new Intent(this, QRCodeActivity.class);
        startActivity(intent);
    }
}
//
//
//
//    TextView type1;
//    TextView price1;
////    TextView description1;
//    TextView type2;
//    TextView price2;
//    TextView description2;
////    TextView description2a;
////    TextView description2b;
////    TextView description2c;
//
//    CompositeDisposable compositeDisposable;
//    PriceForTicketsViewModel priceViewModel;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_price);
//
//        /* toolbar */
//        Toolbar toolbar = findViewById(R.id.toolbar_back);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle(R.string.toolbar_price);
//        if (getSupportActionBar() != null){
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//        }
//        /* /toolbar */
//
//        priceViewModel = new PriceForTicketsViewModel(this.getApplication());
//
//        type1 = findViewById(R.id.type1);
//        price1 = findViewById(R.id.price1);
////        description1 = findViewById(R.id.description1);
//        type2 = findViewById(R.id.type2);
//        price2 = findViewById(R.id.price2);
//        description2 = findViewById(R.id.description2);
////        description2a = findViewById(R.id.description2a);
////        description2b = findViewById(R.id.description2b);
////        description2c = findViewById(R.id.description2c);
//
//
//        compositeDisposable = new CompositeDisposable();
//
//        /* GETTING DATA FROM DATABASE - in RXJava (it's not so hard ;)) */
//        Disposable gettingPriceList2 = Maybe.fromCallable(() -> {
//            return priceViewModel.getAllPrices();
//        })
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(prices -> {
//                    /* onSuccess() :) Here we do whatever :)
//                    * For example here we do the DataBinding or RecyclerView stuff */
//
//
//                    String length = Integer.toString(prices.size());
//                    Toast.makeText(this, length, Toast.LENGTH_SHORT).show();
//
////                    for (int i=0; i<prices.size(); i++)
////                        Toast.makeText(this, prices.get(i).getType(), Toast.LENGTH_SHORT).show();
//
////                    type1.setText(prices.get(0).getType());
//////                    description1.setText(prices.get(0).getDescription());
////                    price1.setText(Integer.toString((prices.get(0).getAmount()).intValue()) + " zł");
////                    type2.setText(prices.get(1).getType());
////                    description2.setText(prices.get(1).getDescription());
////                    price2.setText(Integer.toString((prices.get(1).getAmount()).intValue()) + " zł");
//
////                    description2a.setText(prices.get(2).getDescription());
////                    description2b.setText(prices.get(3).getDescription());
////                    description2c.setText(prices.get(4).getDescription());
//
////                    Toast.makeText(this, prices.get(2).getDescription(), Toast.LENGTH_SHORT).show();
//                }
//                ,throwable -> {
//                    /* onError() - here we are sad... :( */
//                    Toast.makeText(this, "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG).show();
//                });
//
//        compositeDisposable.add(gettingPriceList2);
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        LanguageManager.setLocale(this);
//    }
//
////    @Override
////    protected void attachBaseContext(Context base) {
////        super.attachBaseContext(LanguageManager.setLocale(base));
////    }
//
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // handle arrow click here
//        if (item.getItemId() == android.R.id.home) {
//            finish(); // close this activity and return to preview activity (if there is any)
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
////        compositeDisposable.clear();
//    }
//}
