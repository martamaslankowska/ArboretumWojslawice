package arboretum.arboretumwojslawice.Commons.modules;

import arboretum.arboretumwojslawice.View.fragments.RouteFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = RouteModule.class)
    abstract RouteFragment bindRouteFragment();
}
