package arboretum.arboretumwojslawice.Commons.di;

import javax.inject.Singleton;

import arboretum.arboretumwojslawice.Commons.modules.DatabaseModule;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilder.class,
        AndroidSupportInjectionModule.class,
        DatabaseModule.class
})
@Singleton
public interface AppComponent extends AndroidInjector<AppController> {
    void inject(AppController appController);

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<AppController> {
        @Override
        public abstract AppComponent build();
    }
}