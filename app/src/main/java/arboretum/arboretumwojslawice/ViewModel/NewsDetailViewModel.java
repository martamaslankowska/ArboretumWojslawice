package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.NewsRepository;
import arboretum.arboretumwojslawice.Model.businessentity.News;
import arboretum.arboretumwojslawice.Model.businessentity.NewsImage;

public class NewsDetailViewModel {

    @Inject
    NewsRepository newsRepository;

    @Inject
    NewsDetailViewModel() {}

    public List<News> getAllPastNews() {
        return newsRepository.getAllPastNews();
    }

    public News getCurrentNews() {
        return newsRepository.getAllPastNews().get(0);
    }

    public List<NewsImage> getAllImagesById(int id) {
        return newsRepository.getExtraImages(id);
    }

}
