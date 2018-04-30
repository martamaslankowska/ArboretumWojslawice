package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.NewsRepository;
import arboretum.arboretumwojslawice.Model.businessentity.News;

public class NewsViewModel {

    private List<News> mNews;
    NewsRepository mNewsRepo;

    public NewsViewModel() {
        mNewsRepo = new NewsRepository();
    }

    public List<News> getData()
    {
        mNews = mNewsRepo.getNewsForMichal();
        return mNews;
    }
}
