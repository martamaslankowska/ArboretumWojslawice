package arboretum.arboretumwojslawice.Commons.modules;

import arboretum.arboretumwojslawice.View.adapter.PlantAdapter;
import arboretum.arboretumwojslawice.View.fragments.ListOfPlantsFragment;
import dagger.Module;
import dagger.Provides;

@Module
public class ListOfPlantsModule {

    @Provides
    PlantAdapter provideMyAdapter(ListOfPlantsFragment fragment) { return new PlantAdapter(fragment); }
}
