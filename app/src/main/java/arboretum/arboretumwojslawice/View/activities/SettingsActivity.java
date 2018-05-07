package arboretum.arboretumwojslawice.View.activities;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import arboretum.arboretumwojslawice.Commons.LocaleHelper;
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

    @SuppressLint("ResourceAsColor")
    public void polishLanguage(View view) {
        Toast.makeText(getApplicationContext(), "Zmieniono język na polski", Toast.LENGTH_LONG).show();
        setLanguage("pl");
    }

    @SuppressLint("ResourceAsColor")
    public void englishLanguage(View view) {
        Toast.makeText(getApplicationContext(), "Zmieniono język na angielski", Toast.LENGTH_LONG).show();
        setLanguage("en");
    }

    @SuppressLint("ResourceAsColor")
    public void germanLanguage(View view) {
        Toast.makeText(getApplicationContext(), "Przemyśl swoje życie", Toast.LENGTH_LONG).show();
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
        restartApp(getApplicationContext());
    }

    private void restartApp(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = IntentCompat.makeRestartActivityTask(componentName);
        context.startActivity(mainIntent);
        System.exit(0);
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
