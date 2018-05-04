package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

import arboretum.arboretumwojslawice.Model.AppDatabase;
import arboretum.arboretumwojslawice.Model.DAO.HotelDao;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences mPrefs;
    final String INFO = "select_language";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase database = AppDatabase.getAppDatabase(getApplicationContext());

        /* Deciding weather show language screen or not */
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String language = mPrefs.getString(INFO, null);

        if (language == null) {

            /* RANDOM DATABASE QUERY - needed to initialize database */
            HotelDao hotelDao = database.getHotelDao();
            Disposable gettingHotels = Completable.fromCallable(() -> {
                return hotelDao.getAll();
            })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                                /* onSuccess() :) */
                                try {
                                    Toast.makeText(this, "Było odwołanie do bazy i fajnie", Toast.LENGTH_SHORT);
                                } catch (Exception e){
                                    Toast.makeText(this, "Ups, pusta baza :(", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                            ,throwable -> {
                                /* onError() */
                                Toast.makeText(this, "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG).show();
                            });


            Intent intent = new Intent(this, LanguageActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            setLanguage(language);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void setLanguage(String languageCode) {
        Locale myLocale = new Locale(languageCode);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    public boolean tableExists(String databaseName) {
        Cursor c = null;
        SQLiteDatabase mDatabase = openOrCreateDatabase(databaseName, MODE_PRIVATE,null);
        boolean tableExists = false;

        /* get cursor on it */
        try {
            c = mDatabase.query("Prices", null,null, null, null, null, null);
            int nrOfRows = c.getCount();
            tableExists = true;
        } catch (Exception e) {
            /* fail */
            Log.d("ERROR", "Table doesn't exist :(");
        }

        return tableExists;
    }
}



