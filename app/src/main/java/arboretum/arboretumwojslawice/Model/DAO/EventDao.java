package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.EventEntity;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */


@Dao
public interface EventDao {

    @Query("SELECT * FROM EventEntity")
    Maybe<List<EventEntity>> getAll();

    @Query("SELECT * FROM EventEntity WHERE IdEvent IN (:id)")
    EventEntity getById(int id);

    @Query("SELECT * FROM EventEntity WHERE DateBegin IN (:dateBegin)")
    Maybe<List<EventEntity>> getByDateBegin(int dateBegin);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(EventEntity... events);

    @Insert
    void insert(List<EventEntity> events);

    @Delete
    void delete(EventEntity... events);

    @Delete
    void delete(List<EventEntity> events);

    @Update
    void update(EventEntity... events);

    @Update
    void update(List<EventEntity> events);

}