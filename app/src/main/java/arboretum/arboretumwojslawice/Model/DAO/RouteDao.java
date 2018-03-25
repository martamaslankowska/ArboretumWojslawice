package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.RouteEntity;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-23.
 */


@Dao
abstract class RouteDao implements BaseDao<RouteEntity> {

    @Query("SELECT * FROM RouteEntity")
    abstract Maybe<List<RouteEntity>> getAll();

    @Query("SELECT * FROM RouteEntity WHERE IdRoute IN (:id)")
    abstract RouteEntity getById(int id);

    @Query("SELECT * FROM RouteEntity WHERE Name IN (:name) LIMIT 1")
    abstract RouteEntity getByName(int name);


}
