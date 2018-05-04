package arboretum.arboretumwojslawice.Commons.modules;

import arboretum.arboretumwojslawice.View.activities.RouteDetailActivity;
import arboretum.arboretumwojslawice.View.adapter.ViewPagerAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class RouteDetailModule {

    @Provides
    ViewPagerAdapter provideMyAdapter(RouteDetailActivity activity) { return new ViewPagerAdapter(activity, activity);}

}
