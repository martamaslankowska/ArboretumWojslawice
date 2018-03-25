package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.RoutePointEntity;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-23.
 */

@Dao
abstract class RoutePointDao implements BaseDao<RoutePointEntity> {

    @Query("SELECT * FROM RoutePointEntity")
    abstract Maybe<List<RoutePointEntity>> getAll();

    @Query("SELECT * FROM RoutePointEntity WHERE IdRoute IN (:idRoute) AND idLocations IN (:idLocation)")
    abstract RoutePointEntity getByIds(int idRoute, int idLocation);

    @Query("SELECT * FROM RoutePointEntity WHERE IdRoute IN (:idRoute)")
    abstract Maybe<List<RoutePointEntity>> getByRouteId(int idRoute);



}
