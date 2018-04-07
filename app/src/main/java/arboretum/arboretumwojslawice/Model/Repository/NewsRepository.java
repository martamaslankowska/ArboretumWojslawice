package arboretum.arboretumwojslawice.Model.Repository;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.DAO.NewsDao;
import arboretum.arboretumwojslawice.Model.DAO.NewsTranslationDao;
import arboretum.arboretumwojslawice.Model.businessentity.News;

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

    public  News getById(int id) {
        return newsDao.getById(id, languageCode);
    }

}
