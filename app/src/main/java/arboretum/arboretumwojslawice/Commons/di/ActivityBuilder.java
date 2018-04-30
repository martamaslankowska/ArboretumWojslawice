package arboretum.arboretumwojslawice.Commons.di;

import arboretum.arboretumwojslawice.Commons.modules.MainActivityModule;
import arboretum.arboretumwojslawice.Commons.modules.RouteModule;
import arboretum.arboretumwojslawice.View.activities.MainActivity;
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
}
