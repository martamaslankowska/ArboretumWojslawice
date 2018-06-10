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
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
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
    final String SYNC = "sync";
    @Inject
    Locale myLocale;
    RadioGroup rg;
    RadioButton buttonPL, buttonEN, buttonDE;
    String language, languageCode;
    CheckBox cb;
    Boolean isSync;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        rg = findViewById(R.id.radioGroup_language);
        cb = findViewById(R.id.checkBox_sync);
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
        isSync = mPrefs.getBoolean(SYNC, false);

        if (isSync) {
            cb.setChecked(true);
        }
        else {
            cb.setChecked(false);
        }

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

    public void changeSync(View view) {
        if (isSync) {
            isSync = false;
            saveSyncToSharedPreferences(false);
        }
        else {
            isSync = true;
            saveSyncToSharedPreferences(true);
        }
    }


    public void aboutUsClick(View view) {
//        Toast.makeText(this, "Był onClick ^_^", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivityForResult(intent, 123);
    }


    @SuppressLint("ResourceAsColor")
    public void polishLanguage(View view) {
        if (!languageCode.equals(PL)) {
            String toastText = "Zmieniono język na polski.\nAplikacja zostanie uruchomiona ponownie.";
            Toast toast = makeToast(toastText);
            toast.show();
            buttonPL.setChecked(true);
            setLanguage(PL);
        }
    }

    @SuppressLint("ResourceAsColor")
    public void englishLanguage(View view) {
        if (!languageCode.equals(EN)) {
            String toastText = "The language has been changed to English.\nApplication will restart.";
            Toast toast = makeToast(toastText);
            toast.show();
            buttonEN.setChecked(true);
            setLanguage(EN);
        }
    }

    @SuppressLint("ResourceAsColor")
    public void germanLanguage(View view) {
        if (!languageCode.equals(DE)) {
            String toastText = "Die Sprache wurde auf Deutsch geändert.\nDie Anwendung wird neu gestartet.";
            Toast toast = makeToast(toastText);
            toast.show();
            buttonDE.setChecked(true);
            setLanguage(DE);
        }
    }


    public Toast makeToast(String textString) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_language_change, findViewById(R.id.custom_toast_container));

        TextView text = layout.findViewById(R.id.text);
        text.setText(textString);

        Toast toast = new Toast(getApplicationContext());
//        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
//        toast.show();
        return toast;
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
        }, 1800);

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

    private void saveSyncToSharedPreferences(Boolean sync) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(SYNC, sync);
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
