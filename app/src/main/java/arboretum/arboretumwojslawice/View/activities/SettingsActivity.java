package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

import arboretum.arboretumwojslawice.R;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences mPrefs;
    final String INFO = "select_language";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        /* toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_settings);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        /* /toolbar */

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
        Toast.makeText(getApplicationContext(), "UWAGA!!! Język zmieni się tylko wtedy gdy wyłączysz aplikację i włączysz ją jeszcze raz!",
                Toast.LENGTH_LONG).show();
        this.recreate();
    }

    private void saveLanguageToSharedPreferences(String languageCode) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(INFO, languageCode);
        editor.commit(); // Very important to save the preference

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
