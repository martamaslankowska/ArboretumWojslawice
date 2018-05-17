package arboretum.arboretumwojslawice.Model.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.DAO.NewsDao;
import arboretum.arboretumwojslawice.Model.DAO.NewsTranslationDao;
import arboretum.arboretumwojslawice.Model.businessentity.News;
import arboretum.arboretumwojslawice.R;

public class NewsRepository extends BaseRepository {
    @Inject
    NewsDao newsDao;

    @Inject
    NewsTranslationDao newsTranslationDao;

    @Inject
    public NewsRepository() {}

    public List<News> getAllNews() {
        return newsDao.getAll(languageCode);
    }

    public List<News> getAllPastNews() {
        int today = getToday();
        return newsDao.getAllPast(today, languageCode);
    }

    public News getById(int id) {
        return newsDao.getById(id, languageCode);
    }

}
