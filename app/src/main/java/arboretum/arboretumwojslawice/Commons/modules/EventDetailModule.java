package arboretum.arboretumwojslawice.Commons.modules;

import arboretum.arboretumwojslawice.View.adapter.EventDetailAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class EventDetailModule {

    @Provides
    EventDetailAdapter provideMyAdapter() { return new EventDetailAdapter(); }
}
