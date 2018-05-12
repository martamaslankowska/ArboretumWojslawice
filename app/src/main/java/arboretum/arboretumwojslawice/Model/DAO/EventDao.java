package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.EventEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Event;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Komputer on 2018-03-24.
 */


@Dao
public abstract class EventDao extends BaseDao<EventEntity> {

    @Query("SELECT Events.IdEvent, Type, Name, DateBegin, DateEnd, TimeBegin, TimeEnd, Description " +
            "FROM Events LEFT JOIN EventsTranslations ON Events.IdEvent = EventsTranslations.IdEvent " +
            "WHERE TranslationCode IN (:translationCode)")
    public abstract List<Event> getAll(String translationCode);

    @Query("SELECT Events.IdEvent, Type, Name, DateBegin, DateEnd, TimeBegin, TimeEnd, Description " +
            "FROM Events LEFT JOIN EventsTranslations ON Events.IdEvent = EventsTranslations.IdEvent " +
            "WHERE Events.IdEvent IN (:id) AND TranslationCode IN (:translationCode)")
    public abstract Event getById(int id, String translationCode);

    @Query("SELECT Events.IdEvent, Type, Name, DateBegin, DateEnd, TimeBegin, TimeEnd, Description " +
            "FROM Events LEFT JOIN EventsTranslations ON Events.IdEvent = EventsTranslations.IdEvent " +
            "WHERE DateBegin IN (:dateBegin) AND TranslationCode IN (:translationCode)")
    public abstract List<Event> getAllByDateBegin(int dateBegin, String translationCode);

    @Query("SELECT Events.IdEvent, Type, Name, DateBegin, DateEnd, TimeBegin, TimeEnd, Description " +
            "FROM Events LEFT JOIN EventsTranslations ON Events.IdEvent = EventsTranslations.IdEvent " +
            "WHERE (DateBegin IN (:date) OR (DateBegin < (:date) AND DateEnd >= (:date))) " +
            "AND TranslationCode IN (:translationCode)")
    public abstract List<Event> getAllDuringGivenDate(int date, String translationCode);

    @Query("SELECT DISTINCT Events.DateBegin " +
            "FROM Events")
    public abstract List<Integer> getAllDateBegin();

    @Query("SELECT Date " +
            "FROM (SELECT DISTINCT DateBegin as 'DATE'" +
                "FROM Events LEFT JOIN EventsTranslations ON Events.IdEvent = EventsTranslations.IdEvent " +
                "WHERE DateBegin >= (:actualDate) AND TranslationCode IN (:translationCode) " +
                "UNION ALL " +
                "SELECT DISTINCT DateEnd as 'DATE' " +
                "FROM Events LEFT JOIN EventsTranslations ON Events.IdEvent = EventsTranslations.IdEvent " +
                "WHERE DateBegin < (:actualDate) AND DateEnd >= (:actualDate) AND TranslationCode IN (:translationCode))" +
            "ORDER BY Date ")
    public abstract List<Integer> getAllDateFromToday(int actualDate, String translationCode);

    /* probably temporary */
    @Query("DELETE FROM Events")
    public abstract void deleteAll();

}