package arboretum.arboretumwojslawice.ViewModel;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.NewsRepository;

public class NewsDetailViewModel {

    @Inject
    NewsRepository newsRepository;

    @Inject
    NewsDetailViewModel() {}
}
