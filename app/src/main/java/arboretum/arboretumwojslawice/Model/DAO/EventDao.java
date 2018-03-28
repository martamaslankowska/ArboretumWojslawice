package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.EventEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Event;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */


@Dao
public abstract class EventDao extends BaseDao<EventEntity> {

    @Query("SELECT Events.IdEvent, Type, Name, DateBegin, DateEnd, TimeBegin, TimeEnd, Description " +
            "FROM Events LEFT JOIN EventsTranslations ON Events.IdEvent = EventsTranslations.IdEvent")
    public abstract Maybe<List<Event>> getAll();

    @Query("SELECT Events.IdEvent, Type, Name, DateBegin, DateEnd, TimeBegin, TimeEnd, Description " +
            "FROM Events LEFT JOIN EventsTranslations ON Events.IdEvent = EventsTranslations.IdEvent " +
            "WHERE Events.IdEvent IN (:id)")
    public abstract Event getById(int id);

    @Query("SELECT Events.IdEvent, Type, Name, DateBegin, DateEnd, TimeBegin, TimeEnd, Description " +
            "FROM Events LEFT JOIN EventsTranslations ON Events.IdEvent = EventsTranslations.IdEvent " +
            "WHERE DateBegin IN (:dateBegin)")
    public abstract Maybe<List<Event>> getAllByDateBegin(int dateBegin);


}