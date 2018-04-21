package arboretum.arboretumwojslawice.Commons.modules;

import javax.inject.Singleton;

import arboretum.arboretumwojslawice.View.adapter.RouteAdapter;
import arboretum.arboretumwojslawice.View.fragments.RouteFragment;
import arboretum.arboretumwojslawice.ViewModel.RouteViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class RouteModule {

    @Provides
    RouteAdapter provideMyAdapter(RouteFragment fragment) {
        return new RouteAdapter(fragment);
    }
}
