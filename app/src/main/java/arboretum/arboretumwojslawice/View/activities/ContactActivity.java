package arboretum.arboretumwojslawice.View.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.businessentity.Hotel;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.ContactViewModel;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ContactActivity extends DaggerAppCompatActivity {

    TextView hotel;
    ImageView hotelImage;
    CompositeDisposable compositeDisposable;
    //HotelRepository hotelRepo;
    @Inject
    protected ContactViewModel contactViewModel;

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

        //AppDatabase db = AppDatabase.getAppDatabase(getApplication().getApplicationContext());
        //hotelRepo = new HotelRepository(db);

        hotel = findViewById(R.id.hotelText);
        hotelImage = findViewById(R.id.hotelImageView);
        Hotel hotelObject = new Hotel(1, "Aaa", "", 0, "", 9.0, 2.0, "news2");

        try {
//            String imageName = "news1"; // store only image name in database(means not   store "R.drawable.image") like "image".
//            int id = getResources().getIdentifier("arboretum.arboretumwojslawice:drawable/" + imageName, null, null);
            int id = hotelObject.getImageId(this);
            hotelImage.setImageResource(id);
        } catch (Exception e) {
            Toast.makeText(this, "Coś z tą bazką nie pykło....", Toast.LENGTH_SHORT).show();
        }

        compositeDisposable = new CompositeDisposable();

        /* GETTING DATA FROM DATABASE - in RXJava (it's not so hard ;)) */

        Disposable gettingHotels = Maybe.fromCallable(() -> {
            return contactViewModel.getAllHotels();
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(hotels -> {
                            /* onSuccess() :) */
                            try {
                                hotel.setText(hotels.get(0).getName());
                                Toast.makeText(this, "Uuu, są dane w bazie! O.o", Toast.LENGTH_SHORT).show();
                                Toast.makeText(this, "I to w dodatku w ilości sztuk " + String.valueOf(hotels.size()), Toast.LENGTH_SHORT).show();
                            } catch (Exception e){
                                Toast.makeText(this, "Ups, pusta baza :(", Toast.LENGTH_SHORT).show();
                            }
                        }
                        ,throwable -> {
                            /* onError() */
                            Toast.makeText(this, "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG).show();
                        });

        compositeDisposable.add(gettingHotels);

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
