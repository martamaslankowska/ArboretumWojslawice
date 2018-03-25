package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.RouteEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Route;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-23.
 */


@Dao
abstract class RouteDao implements BaseDao<RouteEntity> {

    @Query("SELECT Routes.IdRoute, Length, Time, MapImage, Name, Description " +
            "FROM Routes LEFT JOIN RoutesTranslations ON Routes.IdRoute = RoutesTranslations.IdRoute")
    abstract Maybe<List<Route>> getAll();

    @Query("SELECT PointOrder, X, Y " +
            "FROM Routes LEFT JOIN RoutePoints ON Routes.IdRoute = RoutePoints.IdRoute " +
            "LEFT JOIN Locations ON RoutePoints.IdLocation = Locations.IdLocation " +
            "WHERE IdRoute IN (:idRoute)")
    abstract Maybe<List<Route.PlantOnRoute>> getRoutePointsByRouteId(int idRoute);

    @Query("SELECT IdPlant " +
            "FROM Routes LEFT JOIN RoutePoints ON Routes.IdRoute = RoutePoints.IdRoute " +
            "LEFT JOIN Locations ON RoutePoints.IdLocation = Locations.IdLocation " +
            "WHERE IdRoute IN (:idRoute)")
    abstract Maybe<List<Integer>> getPlantsIdByRouteId(int idRoute);

    @Query("SELECT Routes.IdRoute, Length, Time, MapImage, Name, Description " +
            "FROM Routes LEFT JOIN Locations ON Routes.IdRoute = Locations.IdR " +
            "WHERE IdRoute IN (:id)")
    abstract Route getById(int id);

    @Query("SELECT Routes.IdRoute, Length, Time, MapImage, Name, Description " +
            "FROM Routes LEFT JOIN RoutesTranslations ON Routes.IdRoute = RoutesTranslations.IdRoute " +
            "WHERE Name IN (:name) LIMIT 1")
    abstract Route getByName(int name);


}
