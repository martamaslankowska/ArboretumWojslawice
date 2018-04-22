package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

import arboretum.arboretumwojslawice.R;

public class LanguageActivity extends AppCompatActivity {

    SharedPreferences mPrefs;
    final String INFO = "select_language";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void halo(View view) {
        Toast.makeText(this, "Czeeeeść :)", Toast.LENGTH_SHORT).show();
    }


    public void polishLanguage(View view) {
        Toast.makeText(getApplicationContext(), "Ha! Nacisnąłeś frajerze!", Toast.LENGTH_LONG).show();
        setLanguage("en");
    }

    /* Avaliable codes: 'pl', 'en' and 'de' */
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
