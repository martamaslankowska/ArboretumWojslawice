package arboretum.arboretumwojslawice.Commons.components;

import javax.inject.Singleton;

import arboretum.arboretumwojslawice.Commons.di.AppModule;
import arboretum.arboretumwojslawice.Commons.modules.DatabaseModule;
import arboretum.arboretumwojslawice.View.activities.PriceActivity;
import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, DatabaseModule.class})
public interface PriceComponent {

//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        Builder application(Application application);
//        PriceComponent build();
//    }

    void inject(PriceActivity priceActivity);

}