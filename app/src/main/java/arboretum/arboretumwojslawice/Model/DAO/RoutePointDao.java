package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.RoutePoint;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-23.
 */

@Dao
public interface RoutePointDao {

    @Query("SELECT * FROM RoutePoints")
    Maybe<List<RoutePoint>> getAll();

    @Query("SELECT * FROM RoutePoints WHERE IdRoute IN (:idRoute) AND IdCoordinate IN (:idCoordinate)")
    RoutePoint getByIds(int idRoute, int idCoordinate);

    @Query("SELECT * FROM RoutePoints WHERE IdRoute IN (:idRoute)")
    Maybe<List<RoutePoint>> getByRouteId(int idRoute);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(RoutePoint... routePoints);

    @Insert
    void insert(List<RoutePoint> routePoints);

    @Delete
    void delete(RoutePoint... routePoints);

    @Delete
    void delete(List<RoutePoint> routePoints);

    @Update
    void update(RoutePoint... routePoints);

    @Update
    void update(List<RoutePoint> routePoints);

}
