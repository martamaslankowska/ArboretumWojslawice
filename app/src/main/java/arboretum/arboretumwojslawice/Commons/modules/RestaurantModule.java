package arboretum.arboretumwojslawice.Commons.modules;

import arboretum.arboretumwojslawice.View.adapter.RestaurantAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by weronika on 09.05.2018.
 */

@Module
public class RestaurantModule {
    @Provides
    RestaurantAdapter provideMyAdapter() { return new RestaurantAdapter(); }
}
