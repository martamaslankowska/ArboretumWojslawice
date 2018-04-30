package arboretum.arboretumwojslawice.Commons.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Locale;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Komputer on 2018-03-27.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Named("languageCode")
    @Provides
    String provideLanguageCode(AppController appcontroller) {
        String value = PreferenceManager.getDefaultSharedPreferences(appcontroller.getApplicationContext()).getString("select_language","pl");
        return value;
    }

    @Named("locale")
    @Provides
    Locale provideLocale(AppController appcontroller) {
        Locale value = appcontroller.getResources().getConfiguration().locale;
        return value;
    }
}
