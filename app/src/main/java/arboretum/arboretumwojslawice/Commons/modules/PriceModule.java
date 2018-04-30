package arboretum.arboretumwojslawice.Commons.modules;

import arboretum.arboretumwojslawice.View.fragments.PriceForServicesFragment;
import arboretum.arboretumwojslawice.View.fragments.PriceForTicketsFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PriceModule {
    @ContributesAndroidInjector(modules = PriceForTicketsModule.class)
    abstract PriceForTicketsFragment bindPriceForTicketsFragment();

    @ContributesAndroidInjector(modules = PriceForServicesModule.class)
    abstract PriceForServicesFragment bindPriceForServicesFragment();
}
