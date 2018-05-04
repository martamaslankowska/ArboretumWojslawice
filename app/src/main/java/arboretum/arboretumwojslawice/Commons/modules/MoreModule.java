package arboretum.arboretumwojslawice.Commons.modules;

import arboretum.arboretumwojslawice.View.adapter.MoreAdapter;
import arboretum.arboretumwojslawice.View.fragments.MoreFragment;
import dagger.Module;
import dagger.Provides;

@Module
public class MoreModule {

    @Provides
    MoreAdapter provideMyAdapter(MoreFragment moreFragment) {
        return new MoreAdapter(moreFragment);
    }
}
