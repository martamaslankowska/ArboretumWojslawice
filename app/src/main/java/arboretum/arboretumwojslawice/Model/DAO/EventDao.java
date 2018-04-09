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
    public abstract  Event getById(int id, String translationCode);

    @Query("SELECT Events.IdEvent, Type, Name, DateBegin, DateEnd, TimeBegin, TimeEnd, Description " +
            "FROM Events LEFT JOIN EventsTranslations ON Events.IdEvent = EventsTranslations.IdEvent " +
            "WHERE DateBegin IN (:dateBegin) AND TranslationCode IN (:translationCode)")
    public abstract List<Event> getAllByDateBegin(int dateBegin, String translationCode);


    /* probably temporary */
    @Query("DELETE FROM Events")
    public abstract void deleteAll();

}