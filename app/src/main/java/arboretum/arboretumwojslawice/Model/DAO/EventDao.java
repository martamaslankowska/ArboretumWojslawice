package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.AttractionEntity;
import arboretum.arboretumwojslawice.Model.Entity.EventEntity;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */


@Dao
abstract class EventDao implements BaseDao<EventEntity>  {

    @Query("SELECT * FROM EventEntity")
    abstract Maybe<List<EventEntity>> getAll();

    @Query("SELECT * FROM EventEntity WHERE IdEvent IN (:id)")
    abstract EventEntity getById(int id);

    @Query("SELECT * FROM EventEntity WHERE DateBegin IN (:dateBegin)")
    abstract Maybe<List<EventEntity>> getByDateBegin(int dateBegin);


}