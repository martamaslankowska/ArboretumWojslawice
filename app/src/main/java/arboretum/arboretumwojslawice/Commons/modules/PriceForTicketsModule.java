package arboretum.arboretumwojslawice.Commons.modules;

import arboretum.arboretumwojslawice.Commons.di.AppController;
import arboretum.arboretumwojslawice.View.adapter.PriceForTicketsAdapter;
import arboretum.arboretumwojslawice.ViewModel.PriceForTicketsViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class PriceForTicketsModule {

    @Provides
    PriceForTicketsAdapter provideMyAdapter() {
        return new PriceForTicketsAdapter();
    }

}
