package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.util.Locale;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.Globals;
import arboretum.arboretumwojslawice.Model.AppDatabase;
import arboretum.arboretumwojslawice.Model.DAO.HotelDao;
import arboretum.arboretumwojslawice.ViewModel.SplashViewModel;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends DaggerAppCompatActivity {

    SharedPreferences mPrefs;
    final String INFO = "select_language";

    @Inject
    SplashViewModel splashViewModel;
    private CompositeDisposable compositeDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase database = AppDatabase.getAppDatabase(getApplicationContext());
        compositeDisposable = new CompositeDisposable();

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

            int eventImageResource = splashViewModel.getEventImageId(getApplicationContext());
            Globals.eventImageResource = eventImageResource;

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

}



