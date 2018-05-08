package arboretum.arboretumwojslawice.Commons.modules;

import arboretum.arboretumwojslawice.View.adapter.AttractionAdapter;
import arboretum.arboretumwojslawice.View.adapter.FavouritesAdapter;
import arboretum.arboretumwojslawice.View.fragments.AttractionFragment;
import arboretum.arboretumwojslawice.View.fragments.FavouritesFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by weronika on 08.05.2018.
 */

@Module
public class AttractionModule {
    @Provides
    AttractionAdapter provideMyAdapter() { return new AttractionAdapter(); }
}
