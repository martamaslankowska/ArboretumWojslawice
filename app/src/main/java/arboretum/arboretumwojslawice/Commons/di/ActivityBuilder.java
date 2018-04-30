package arboretum.arboretumwojslawice.Commons.di;

import arboretum.arboretumwojslawice.Commons.modules.EventModule;
import arboretum.arboretumwojslawice.Commons.modules.MainActivityModule;
import arboretum.arboretumwojslawice.Commons.modules.PlantModule;
import arboretum.arboretumwojslawice.Commons.modules.RouteModule;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.View.activities.EventActivity;
import arboretum.arboretumwojslawice.View.activities.MainActivity;
import arboretum.arboretumwojslawice.View.activities.PlantActivity;
import arboretum.arboretumwojslawice.View.activities.PlantDetailActivity;
import arboretum.arboretumwojslawice.View.fragments.RouteFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract PlantDetailActivity bindPlantDetailActivity();

    @ContributesAndroidInjector(modules = PlantModule.class)
    abstract PlantActivity bindPlantActivity();

    @ContributesAndroidInjector(modules = EventModule.class)
    abstract EventActivity bindEventActivity();
}
