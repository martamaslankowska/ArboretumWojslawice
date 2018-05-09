package arboretum.arboretumwojslawice.Commons.modules;

import arboretum.arboretumwojslawice.Model.businessentity.Attraction;
import arboretum.arboretumwojslawice.View.activities.PlantDetailActivity;
import arboretum.arboretumwojslawice.View.fragments.AttractionFragment;
import arboretum.arboretumwojslawice.View.fragments.FavouritesFragment;
import arboretum.arboretumwojslawice.View.fragments.HomeFragment;
import arboretum.arboretumwojslawice.View.fragments.HotelFragment;
import arboretum.arboretumwojslawice.View.fragments.MoreFragment;
import arboretum.arboretumwojslawice.View.fragments.RouteFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = RouteModule.class)
    abstract RouteFragment bindRouteFragment();

    @ContributesAndroidInjector(modules = NewsModule.class)
    abstract HomeFragment bindHomeFragment();

    @ContributesAndroidInjector(modules = MoreModule.class)
    abstract MoreFragment bindMoreFragment();

    @ContributesAndroidInjector(modules = FavouritesModule.class)
    abstract FavouritesFragment bindFavouritesFragment();

    @ContributesAndroidInjector(modules = AttractionModule.class)
    abstract AttractionFragment bindAttractionFragment();

    @ContributesAndroidInjector(modules = HotelModule.class)
    abstract HotelFragment bindHotelFragment();

}
