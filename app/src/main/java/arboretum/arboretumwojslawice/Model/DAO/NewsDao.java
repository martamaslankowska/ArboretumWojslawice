package arboretum.arboretumwojslawice.Model.DAO;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.EventEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Event;
import arboretum.arboretumwojslawice.Model.businessentity.News;

@Dao
public abstract class NewsDao extends BaseDao<EventEntity> {

    @Query("SELECT News.IdNews, Name, Date, Description, Image " +
            "FROM News LEFT JOIN NewsTranslations ON News.IdNews = NewsTranslations.IdNews " +
            "WHERE TranslationCode IN (:translationCode)")
    public abstract List<News> getAll(String translationCode);

    @Query("SELECT News.IdNews, Name, Date, Description, Image " +
            "FROM News LEFT JOIN NewsTranslations ON News.IdNews = NewsTranslations.IdNews " +
            "WHERE News.IdNews IN (:id) AND TranslationCode IN (:translationCode)")
    public abstract  News getById(int id, String translationCode);

    @Query("SELECT News.IdNews, Name, Date, Description, Image " +
            "FROM News LEFT JOIN NewsTranslations ON News.IdNews = NewsTranslations.IdNews " +
            "WHERE Date <= (:today) AND TranslationCode IN (:translationCode) " +
            "ORDER BY Date DESC")
    public abstract List<News> getAllPast(int today, String translationCode);



//    @Query("SELECT News.IdNews, Name, Date, Description " +
//            "FROM News LEFT JOIN NewsTranslations ON News.IdNews = NewsTranslations.IdNews " +
//            "WHERE Date IN (:date) AND TranslationCode IN (:translationCode) LIMIT 1")
//    public abstract News getNearestDateNews(int date, String translationCode);


}