package arboretum.arboretumwojslawice.Model.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.DAO.NewsDao;
import arboretum.arboretumwojslawice.Model.DAO.NewsTranslationDao;
import arboretum.arboretumwojslawice.Model.businessentity.News;
import arboretum.arboretumwojslawice.R;

public class NewsRepository extends BaseRepository{
    @Inject
    NewsDao newsDao;
    @Inject
    NewsTranslationDao newsTranslationDao;

    @Inject
    public NewsRepository() {}

    public List<News> getAllNews() {
        return newsDao.getAll(languageCode);
    }

    public News getById(int id) {
        return newsDao.getById(id, languageCode);
    }
    
    public List<News> getNewsForMichal()
    {
        List<News> mNews = new ArrayList<News>();
        mNews.add(new News(1,"News_1", "Opis_1", R.drawable.news1, 12042018));
        mNews.add(new News(2,"News_2", "Opis_2", R.drawable.news2, 18042018));
        mNews.add(new News(3,"News_3", "Opis_3", R.drawable.news3, 21042018));
        return mNews;
    }

}
