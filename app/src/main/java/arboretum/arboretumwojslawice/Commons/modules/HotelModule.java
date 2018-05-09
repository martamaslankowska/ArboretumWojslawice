package arboretum.arboretumwojslawice.Commons.modules;

import arboretum.arboretumwojslawice.View.adapter.HotelAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by weronika on 09.05.2018.
 */

@Module
public class HotelModule {
    @Provides
    HotelAdapter provideMyAdapter() { return new HotelAdapter(); }
}
