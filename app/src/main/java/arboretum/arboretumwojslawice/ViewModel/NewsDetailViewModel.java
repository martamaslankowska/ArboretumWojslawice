package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.NewsRepository;
import arboretum.arboretumwojslawice.Model.businessentity.News;

public class NewsDetailViewModel {

    @Inject
    NewsRepository newsRepository;

    @Inject
    NewsDetailViewModel() {}

    public List<News> getAllPastNews() {
        return newsRepository.getAllPastNews();
    }
}
