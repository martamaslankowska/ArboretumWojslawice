package arboretum.arboretumwojslawice.View.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import arboretum.arboretumwojslawice.Model.AppDatabase;
import arboretum.arboretumwojslawice.Model.Repository.HotelRepository;
import arboretum.arboretumwojslawice.Model.Repository.PriceRepository;
import arboretum.arboretumwojslawice.R;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ContactActivity extends AppCompatActivity {

    TextView hotel;
    CompositeDisposable compositeDisposable;
    HotelRepository hotelRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        /* toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_contact);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        /* /toolbar */

        AppDatabase db = AppDatabase.getAppDatabase(getApplication().getApplicationContext());
        hotelRepo = new HotelRepository(db);

        hotel = findViewById(R.id.hotelText);

        compositeDisposable = new CompositeDisposable();

        /* GETTING DATA FROM DATABASE - in RXJava (it's not so hard ;)) */
        Disposable gettingPriceList2 = Maybe.fromCallable(() -> {
            return hotelRepo.getAllHotels();
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(hotels -> {
                            /* onSuccess() :) Here we do whatever :)
                             * For example here we do the DataBinding or RecyclerView stuff */
                            hotel.setText(hotels.get(0).getName());
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
