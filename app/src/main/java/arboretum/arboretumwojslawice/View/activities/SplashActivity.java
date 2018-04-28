package arboretum.arboretumwojslawice.View.activities;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import arboretum.arboretumwojslawice.Model.AppDatabase;
import arboretum.arboretumwojslawice.Model.DAO.AttractionDao;
import arboretum.arboretumwojslawice.Model.DAO.HotelDao;
import arboretum.arboretumwojslawice.Model.DatabaseConnection;
import arboretum.arboretumwojslawice.Model.DatabaseHelper;
import arboretum.arboretumwojslawice.Model.DziadekDatabaseHelper;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences mPrefs;
    final String INFO = "select_language";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /* COPYING DATABASE */

//        DatabaseHelper myDbHelper = new DatabaseHelper(this);
//
//        try {
//            myDbHelper.createDatabase();
//        } catch (IOException ioe) {
//            throw new Error("Unable to create Database");
//        }
//
//        try {
//            myDbHelper.openDatabase();
//        } catch(SQLException sqle){
//            throw sqle;
//        }
//
//        /* Checking if table exists in the database */
//        boolean exists = tableExists((DatabaseHelper.DB_NAME + DatabaseHelper.DB_EX));

        /* FINISHED COPING DATABASE */




//        /* USING ROOM DATABASE */
        AppDatabase database = AppDatabase.getAppDatabase(getApplicationContext());
//        HotelDao hotelDao = database.getHotelDao();
//
//
//        /* TESTING DZIADEK SOLUTION FOR DATABASE COPY */
//        DziadekDatabaseHelper dziadekDbHelper = new DziadekDatabaseHelper();
//        try {
//            dziadekDbHelper.execute(this);
//        } catch (Exception e) {
//            Log.e("DB - Splash screen", e.getMessage());
//        }


        /* Deciding weather show language screen or not */

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String language = mPrefs.getString(INFO, null);

        if (language == null) {
            /* DATA INTO DATABASE INSERT */
//            DatabaseConnection dbConnect = new DatabaseConnection(getApplicationContext(), database);
//            dbConnect.execute();

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



