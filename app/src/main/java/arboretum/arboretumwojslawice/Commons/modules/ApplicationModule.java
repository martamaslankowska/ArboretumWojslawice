package arboretum.arboretumwojslawice.Commons.modules;

import android.app.Application;

import java.util.Locale;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Komputer on 2018-03-27.
 */

@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(final Application application) {
        mApplication = application;
    }

    @Provides
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    public Locale provideLocale() {
        return mApplication.getResources().getConfiguration().locale;
    }

    /* Not able to find @ApplicationContext... */
//    @Provides
//    @ApplicationContext
//    public Context provideApplicationContext() {
//        return mApplication;
//    }

}
