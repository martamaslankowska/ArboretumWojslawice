package arboretum.arboretumwojslawice.Commons.modules;

import arboretum.arboretumwojslawice.View.adapter.FavouritesAdapter;
import arboretum.arboretumwojslawice.View.fragments.FavouritesFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by weronika on 06.05.2018.
 */

@Module
public class FavouritesModule {
    @Provides
    FavouritesAdapter provideMyAdapter(FavouritesFragment fragment) { return new FavouritesAdapter(fragment); }
}
