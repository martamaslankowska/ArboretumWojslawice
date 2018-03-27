package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.RouteEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Route;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-23.
 */


@Dao
public abstract class RouteDao extends BaseDao<RouteEntity> {

    @Query("SELECT Routes.IdRoute, Length, Time, MapImage, Name, Description " +
            "FROM Routes LEFT JOIN RoutesTranslations ON Routes.IdRoute = RoutesTranslations.IdRoute")
    abstract Cursor getAll();

    @Query("SELECT PointOrder, X, Y " +
            "FROM Routes LEFT JOIN RoutePoints ON Routes.IdRoute = RoutePoints.IdRoute " +
            "LEFT JOIN Locations ON RoutePoints.IdLocation = Locations.IdLocation " +
            "WHERE Routes.IdRoute IN (:idRoute)")
    abstract Cursor getRoutePointsByRouteId(int idRoute);

    @Query("SELECT IdPlant " +
            "FROM Routes LEFT JOIN RoutePoints ON Routes.IdRoute = RoutePoints.IdRoute " +
            "LEFT JOIN Locations ON RoutePoints.IdLocation = Locations.IdLocation " +
            "WHERE Routes.IdRoute IN (:idRoute)")
    abstract Cursor getPlantsIdByRouteId(int idRoute);

    @Query("SELECT Routes.IdRoute, Length, Time, MapImage, Name, Description " +
            "FROM Routes LEFT JOIN RoutePoints ON Routes.IdRoute = RoutePoints.IdRoute " +
            "LEFT JOIN RoutesTranslations ON Routes.IdRoute = RoutesTranslations.IdRoute " +
            "WHERE Routes.IdRoute IN (:id)")
    abstract Cursor getById(int id);

    @Query("SELECT Routes.IdRoute, Length, Time, MapImage, RoutesTranslations.Name, Description " +
            "FROM Routes LEFT JOIN RoutesTranslations ON Routes.IdRoute = RoutesTranslations.IdRoute " +
            "WHERE RoutesTranslations.Name IN (:name) LIMIT 1")
    abstract Cursor getByName(int name);


}
