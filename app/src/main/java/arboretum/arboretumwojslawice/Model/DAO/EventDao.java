package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.time.LocalDate;
import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.Event;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */


@Dao
public interface EventDao {

    @Query("SELECT * FROM Events")
    Maybe<List<Event>> getAll();

    @Query("SELECT * FROM Events WHERE IdEvent IN (:id)")
    Event getById(int id);

    @Query("SELECT * FROM Events WHERE DateBegin IN (:dateBegin)")
    Maybe<List<Event>> getByDateBegin(LocalDate dateBegin);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(Event... events);

    @Insert
    void insert(List<Event> events);

    @Delete
    void delete(Event... events);

    @Delete
    void delete(List<Event> events);

    @Update
    void update(Event... events);

    @Update
    void update(List<Event> events);

}