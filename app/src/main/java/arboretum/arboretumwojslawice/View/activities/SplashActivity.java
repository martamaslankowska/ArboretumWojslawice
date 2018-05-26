package arboretum.arboretumwojslawice.View.activities;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.DownloadFileFromURL;
import arboretum.arboretumwojslawice.Commons.Globals;
import arboretum.arboretumwojslawice.Model.AppDatabase;
import arboretum.arboretumwojslawice.Model.DAO.HotelDao;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.SplashViewModel;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class SplashActivity extends DaggerAppCompatActivity {

    SharedPreferences mPrefs;
    final String INFO = "select_language";

    @Inject
    SplashViewModel splashViewModel;
    private CompositeDisposable compositeDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isNetworkConnected() && isInternetOn()) {
            new DownloadFileFromURL().execute("http://arboretumdb.cba.pl/ArboretumDB.db");
        }
        AppDatabase database = AppDatabase.getAppDatabase(getApplicationContext());
        compositeDisposable = new CompositeDisposable();

        /* Setting event image in HomeFragment */
        int eventImageResource = splashViewModel.getEventImageId(getApplicationContext());
        Globals.eventImageResource = eventImageResource;


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
                                Toast.makeText(this, "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG);
                            });


            Intent intent = new Intent(this, LanguageActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            /* SETTING DATA FOR NEWS FRAGMENT */

            Disposable cdNews = Maybe.fromCallable(() -> {
                return splashViewModel.getCurrentNews();
            })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(news -> {
                                Globals.currentNews = news;

                            }
                            ,throwable -> {
                                Toast.makeText(this, "Oh no! Something went terribly wrong with news", Toast.LENGTH_LONG);
                            });
            compositeDisposable.add(cdNews);

            Disposable cdEvent = Maybe.fromCallable(() -> {
                return splashViewModel.getEventNameConcatenate(getApplicationContext());
            })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(eventRowList -> {
                                Globals.nearestEvents = eventRowList;
                            }
                            ,throwable -> {
                                Toast.makeText(this, "Oh no! Something went terribly wrong with events", Toast.LENGTH_LONG);
                            });
            compositeDisposable.add(cdEvent);

            Disposable cdPlant = Maybe.fromCallable(() -> {
                return splashViewModel.getRandomSeasonPlant();
            })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(plant -> {
                                Globals.seasonPlant = plant;
                            }
                            ,throwable -> {
                                Toast.makeText(this, "Oh no! Something went terribly wrong with plants", Toast.LENGTH_LONG);
                            });
            compositeDisposable.add(cdPlant);


            setLanguage(language);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    public void setLanguage(String languageCode) {
        Locale myLocale = new Locale(languageCode);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    public boolean isInternetOn() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            Log.v(TAG, "Internet is working");
            // txt_status.setText("Internet is working");
            return true;
        } else {
            // txt_status.setText("Internet Connection Not Present");
            Log.v(TAG, "Internet Connection Not Present");
            return false;
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();

        // return cm.getActiveNetworkInfo() != null;

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}



