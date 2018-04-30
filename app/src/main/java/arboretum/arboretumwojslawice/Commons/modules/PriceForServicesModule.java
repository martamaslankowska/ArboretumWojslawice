package arboretum.arboretumwojslawice.Commons.modules;

import android.app.Activity;

import arboretum.arboretumwojslawice.Commons.di.AppController;
import arboretum.arboretumwojslawice.View.adapter.PriceForServicesAdapter;
import arboretum.arboretumwojslawice.View.fragments.PriceForServicesFragment;
import arboretum.arboretumwojslawice.ViewModel.PriceForServicesViewModel;
import arboretum.arboretumwojslawice.ViewModel.PriceForTicketsViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class PriceForServicesModule {

    @Provides
    PriceForServicesAdapter provideMyAdapter() {
        return new PriceForServicesAdapter();
    }

}
