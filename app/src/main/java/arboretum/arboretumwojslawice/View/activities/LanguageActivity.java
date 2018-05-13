package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import arboretum.arboretumwojslawice.Model.AppDatabase;
import arboretum.arboretumwojslawice.Model.DAO.HotelDao;
import arboretum.arboretumwojslawice.Model.DziadekDatabaseHelper;
import arboretum.arboretumwojslawice.Model.businessentity.Hotel;
import arboretum.arboretumwojslawice.R;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LanguageActivity extends AppCompatActivity {

    SharedPreferences mPrefs;
    final String INFO = "select_language";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        /* DZIADEK SOLUTION FOR DATABASE COPY - working <3 */
        DziadekDatabaseHelper dziadekDbHelper = new DziadekDatabaseHelper();
        try {
            dziadekDbHelper.execute(this);
        } catch (Exception e) {
            Log.e("DB - Splash screen", e.getMessage());
        }

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);




    }

    public void polishLanguage(View view) {
        Toast.makeText(getApplicationContext(), "Polski", Toast.LENGTH_LONG).show();
        setLanguage("pl");
    }

    public void englishLanguage(View view) {
        Toast.makeText(getApplicationContext(), "Angielski", Toast.LENGTH_LONG).show();
        setLanguage("en");
    }

    public void germanLanguage(View view) {
        Toast.makeText(getApplicationContext(), "Niemiecki, brrr...", Toast.LENGTH_LONG).show();
        setLanguage("de");
    }


    /* Available codes: 'pl', 'en' and 'de' */
    public void setLanguage(String languageCode) {
        Locale myLocale = new Locale(languageCode);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

        saveLanguageToSharedPreferences(languageCode);
        startMainActivity();
    }

    private void saveLanguageToSharedPreferences(String languageCode) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(INFO, languageCode);
        editor.commit(); // Very important to save the preference

    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
