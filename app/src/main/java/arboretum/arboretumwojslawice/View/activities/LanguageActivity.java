package arboretum.arboretumwojslawice.View.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import arboretum.arboretumwojslawice.R;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

    }

    public void polishLanguage(View view) {
        Toast.makeText(getApplicationContext(), "Ha! Nacisnąłeś frajerze!", Toast.LENGTH_LONG).show();
    }


}
