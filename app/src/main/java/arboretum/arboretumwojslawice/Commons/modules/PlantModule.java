package arboretum.arboretumwojslawice.Commons.modules;

import arboretum.arboretumwojslawice.View.fragments.ListOfPlantsFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PlantModule {
    @ContributesAndroidInjector(modules = ListOfPlantsModule.class)
    abstract ListOfPlantsFragment bindListOfPlantsFragment();
}
