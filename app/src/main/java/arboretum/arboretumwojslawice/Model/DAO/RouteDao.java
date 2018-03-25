package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.RouteEntity;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-23.
 */


@Dao
public interface RouteDao {

    @Query("SELECT * FROM RouteEntity")
    Maybe<List<RouteEntity>> getAll();

    @Query("SELECT * FROM RouteEntity WHERE IdRoute IN (:id)")
    RouteEntity getById(int id);

    @Query("SELECT * FROM RouteEntity WHERE Name IN (:name) LIMIT 1")
    RouteEntity getByName(int name);



    // INSERT, DELETE and UPDATE

    @Insert
    void insert(RouteEntity... routes);

    @Insert
    void insert(List<RouteEntity> routes);

    @Delete
    void delete(RouteEntity... routes);

    @Delete
    void delete(List<RouteEntity> routes);

    @Update
    void update(RouteEntity... routes);

    @Update
    void update(List<RouteEntity> routes);
}
