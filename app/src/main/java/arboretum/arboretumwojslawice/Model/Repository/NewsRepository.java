package arboretum.arboretumwojslawice.Model.Repository;

import java.util.ArrayList;
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

    public News getById(int id) {
        return newsDao.getById(id, languageCode);
    }
    
    public List<News> getNewsForMichal()
    {
        List<News> mNews = new ArrayList<News>();
        mNews.add(new News(1,"Wiosenny spacer", "WYSTAWA „Fotograficzne debiuty w Wojsławicach”, Tomek CIESIELSKI prezentuje dorobek uczestników warsztatów fotograficznych, wernisaż\n" +
                "o godz. 12.00", "news1", 20180515));
        mNews.add(new News(2,"Kiermasz roślinnych nowości", "godz. 9:00-18:00", "news2", 20180528));
        mNews.add(new News(3,"Japoński spacer", "Drzewa i krzewy Japonii, zaprasza Juzer JURCZYŃSKI, przewodnik przygodniczy, godz. 12:00", "news3", 20180601));
        return mNews;
    }

}
