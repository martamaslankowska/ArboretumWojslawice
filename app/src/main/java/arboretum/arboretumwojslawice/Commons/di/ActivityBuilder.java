package arboretum.arboretumwojslawice.Commons.di;

import arboretum.arboretumwojslawice.Commons.modules.EventModule;
import arboretum.arboretumwojslawice.Commons.modules.MainActivityModule;
import arboretum.arboretumwojslawice.Commons.modules.PlantModule;
import arboretum.arboretumwojslawice.Commons.modules.PriceModule;
import arboretum.arboretumwojslawice.Commons.modules.RouteModule;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.View.activities.ContactActivity;
import arboretum.arboretumwojslawice.View.activities.EventActivity;
import arboretum.arboretumwojslawice.View.activities.EventDetailActivity;
import arboretum.arboretumwojslawice.View.activities.MainActivity;
import arboretum.arboretumwojslawice.View.activities.NavigationActivity;
import arboretum.arboretumwojslawice.View.activities.PlantActivity;
import arboretum.arboretumwojslawice.View.activities.PlantDetailActivity;
import arboretum.arboretumwojslawice.View.activities.PriceActivity;
import arboretum.arboretumwojslawice.View.fragments.RouteFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = PlantModule.class)
    abstract PlantActivity bindPlantActivity();

    @ContributesAndroidInjector(modules = EventModule.class)
    abstract EventActivity bindEventActivity();

    @ContributesAndroidInjector(modules = PriceModule.class)
    abstract PriceActivity bindPriceActivity();

    @ContributesAndroidInjector
    abstract PlantDetailActivity bindPlantDetailActivity();

    @ContributesAndroidInjector
    abstract ContactActivity bindContactActivity();

    @ContributesAndroidInjector
    abstract EventDetailActivity bindEventDetailActivity();

    @ContributesAndroidInjector
    abstract NavigationActivity bindNavigationActivity();
}
