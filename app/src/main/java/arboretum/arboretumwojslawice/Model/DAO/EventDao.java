package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.EventEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Event;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */


@Dao
public abstract class EventDao implements BaseDao<EventEntity> {

    @Query("SELECT Events.IdEvent, Type, Name, DateBegin, DateEnd, TimeBegin, TimeEnd, Description " +
            "FROM Events LEFT JOIN EventsTranslations ON Events.IdEvent = EventsTranslations.IdEvent")
    abstract Maybe<List<Event>> getAll();

    @Query("SELECT Events.IdEvent, Type, Name, DateBegin, DateEnd, TimeBegin, TimeEnd, Description " +
            "FROM Events LEFT JOIN EventsTranslations ON Events.IdEvent = EventsTranslations.IdEvent " +
            "WHERE IdEvent IN (:id)")
    abstract Event getById(int id);

    @Query("SELECT Events.IdEvent, Type, Name, DateBegin, DateEnd, TimeBegin, TimeEnd, Description " +
            "FROM Events LEFT JOIN EventsTranslations ON Events.IdEvent = EventsTranslations.IdEvent " +
            "WHERE DateBegin IN (:dateBegin) " +
            "ORDER BY TimeBegin")
    abstract Maybe<List<Event>> getByDateBegin(int dateBegin);


}