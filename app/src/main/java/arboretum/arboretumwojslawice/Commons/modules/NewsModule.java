package arboretum.arboretumwojslawice.Commons.modules;

import arboretum.arboretumwojslawice.View.adapter.NewsAdapter;
import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {

    @Provides
    NewsAdapter provideMyAdapter() {
        return new NewsAdapter();
    }
}
