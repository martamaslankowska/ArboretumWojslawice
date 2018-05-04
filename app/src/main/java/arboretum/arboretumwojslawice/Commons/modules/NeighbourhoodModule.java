package arboretum.arboretumwojslawice.Commons.modules;

import arboretum.arboretumwojslawice.View.fragments.AttractionFragment;
import arboretum.arboretumwojslawice.View.fragments.HotelFragment;
import arboretum.arboretumwojslawice.View.fragments.RestaurantFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class NeighbourhoodModule {

    @ContributesAndroidInjector
    abstract RestaurantFragment bindRestaurantFragment();

    @ContributesAndroidInjector
    abstract HotelFragment bindHotelFragment();

    @ContributesAndroidInjector
    abstract AttractionFragment bindAttractionFragment();
}
