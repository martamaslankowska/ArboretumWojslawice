package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.RoutePointEntity;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-23.
 */

@Dao
public interface RoutePointDao {

    @Query("SELECT * FROM RoutePointEntity")
    Maybe<List<RoutePointEntity>> getAll();

    @Query("SELECT * FROM RoutePointEntity WHERE IdRoute IN (:idRoute) AND idLocations IN (:idLocation)")
    RoutePointEntity getByIds(int idRoute, int idLocation);

    @Query("SELECT * FROM RoutePointEntity WHERE IdRoute IN (:idRoute)")
    Maybe<List<RoutePointEntity>> getByRouteId(int idRoute);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(RoutePointEntity... routePoints);

    @Insert
    void insert(List<RoutePointEntity> routePoints);

    @Delete
    void delete(RoutePointEntity... routePoints);

    @Delete
    void delete(List<RoutePointEntity> routePoints);

    @Update
    void update(RoutePointEntity... routePoints);

    @Update
    void update(List<RoutePointEntity> routePoints);

}
