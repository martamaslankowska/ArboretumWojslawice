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
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.LanguageManager;
import arboretum.arboretumwojslawice.Commons.LocaleHelper;
import arboretum.arboretumwojslawice.R;

public class SettingsActivity extends AppCompatActivity {

    final String PL = "pl";
    final String EN = "en";
    final String DE = "de";

    SharedPreferences mPrefs;
    final String INFO = "select_language";
    @Inject
    Locale myLocale;
    RadioGroup rg;
    RadioButton buttonPL, buttonEN, buttonDE;
    String language, languageCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        rg = findViewById(R.id.radioGroup_language);
        buttonPL = findViewById(R.id.polishButton);
        buttonEN = findViewById(R.id.englishButton);
        buttonDE = findViewById(R.id.germanButton);


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
        language = LanguageManager.getLanguage(getApplicationContext());

        languageCode = mPrefs.getString(INFO, PL);
        switch(languageCode) {
            case PL:
                buttonPL.setChecked(true);
                break;
            case EN:
                buttonEN.setChecked(true);
                break;
            case DE:
                buttonDE.setChecked(true);
                break;
        }
    }


    @SuppressLint("ResourceAsColor")
    public void polishLanguage(View view) {
        if (!languageCode.equals(PL)) {
            Toast.makeText(getApplicationContext(), "Zmieniono język na polski.\nAplikacja zostanie uruchomiona ponownie.", Toast.LENGTH_SHORT).show();
            buttonPL.setChecked(true);
            setLanguage(PL);
        }
    }

    @SuppressLint("ResourceAsColor")
    public void englishLanguage(View view) {
        if (!languageCode.equals(EN)) {
            Toast.makeText(getApplicationContext(), "The language has been changed to english.\nApplication will restart.", Toast.LENGTH_SHORT).show();
            buttonEN.setChecked(true);
            setLanguage(EN);
        }
    }

    @SuppressLint("ResourceAsColor")
    public void germanLanguage(View view) {
        if (!languageCode.equals(DE)) {
            Toast.makeText(getApplicationContext(), "Die Sprache wurde auf Deutsch geändert.\nDie Anwendung wird neu gestartet.", Toast.LENGTH_SHORT).show();
            buttonDE.setChecked(true);
            setLanguage(DE);
        }
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

        // delay to show message about language change
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                restartApp(getApplicationContext());
            }
        }, 2000);

//        restartApp(getApplicationContext());
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
