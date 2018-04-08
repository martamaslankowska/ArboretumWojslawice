package arboretum.arboretumwojslawice.Commons.components;

import android.app.Application;

import javax.inject.Singleton;

import arboretum.arboretumwojslawice.Commons.modules.ApplicationModule;
import arboretum.arboretumwojslawice.Commons.modules.DatabaseModule;
import arboretum.arboretumwojslawice.Model.AppDatabase;
import arboretum.arboretumwojslawice.Model.DAO.PriceDao;
import arboretum.arboretumwojslawice.View.activities.MainActivity;
import arboretum.arboretumwojslawice.View.activities.PriceActivity;
import dagger.BindsInstance;
import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class, DatabaseModule.class})
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