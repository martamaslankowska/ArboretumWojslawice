package arboretum.arboretumwojslawice.Commons.modules;

import arboretum.arboretumwojslawice.View.activities.EventActivity;
import arboretum.arboretumwojslawice.View.adapter.EventAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class EventModule {

    @Provides
    EventAdapter provideMyAdapter(EventActivity activity) { return new EventAdapter(activity); }
}
