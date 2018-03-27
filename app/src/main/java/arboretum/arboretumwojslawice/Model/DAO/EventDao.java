package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import arboretum.arboretumwojslawice.Model.Entity.EventEntity;

/**
 * Created by Komputer on 2018-03-24.
 */


@Dao
public abstract class EventDao extends BaseDao<EventEntity> {

    @Query("SELECT Events.IdEvent, Type, Name, DateBegin, DateEnd, TimeBegin, TimeEnd, Description " +
            "FROM Events LEFT JOIN EventsTranslations ON Events.IdEvent = EventsTranslations.IdEvent")
    abstract Cursor getAll();

    @Query("SELECT Events.IdEvent, Type, Name, DateBegin, DateEnd, TimeBegin, TimeEnd, Description " +
            "FROM Events LEFT JOIN EventsTranslations ON Events.IdEvent = EventsTranslations.IdEvent " +
            "WHERE Events.IdEvent IN (:id)")
    abstract Cursor getById(int id);

    @Query("SELECT Events.IdEvent, Type, Name, DateBegin, DateEnd, TimeBegin, TimeEnd, Description " +
            "FROM Events LEFT JOIN EventsTranslations ON Events.IdEvent = EventsTranslations.IdEvent " +
            "WHERE DateBegin IN (:dateBegin)")
    abstract Cursor getAllByDateBegin(int dateBegin);


}