package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.RouteEntity;
import arboretum.arboretumwojslawice.Model.businessentity.PointOnRoute;
import arboretum.arboretumwojslawice.Model.businessentity.Route;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Komputer on 2018-03-23.
 */


@Dao
public abstract class RouteDao extends BaseDao<RouteEntity> {

    @Query("SELECT Routes.IdRoute, Length, Time, MapImage, MapImageDetailed, Name, Description " +
            "FROM Routes LEFT JOIN RoutesTranslations ON Routes.IdRoute = RoutesTranslations.IdRoute " +
            "WHERE TranslationCode IN (:translationCode)")
    public abstract List<Route> getAll(String translationCode);

    @Query("SELECT PointOrder, IdPlant, X, Y, Highlighted " +
            "FROM Routes LEFT JOIN RoutePoints ON Routes.IdRoute = RoutePoints.IdRoute " +
            "LEFT JOIN Locations ON RoutePoints.IdLocation = Locations.IdLocation " +
            "WHERE Routes.IdRoute IN (:idRoute)")
    public abstract List<PointOnRoute> getRoutePointsByRouteId(int idRoute);

//    @Query("SELECT IdPlant " +
//            "FROM Routes LEFT JOIN RoutePoints ON Routes.IdRoute = RoutePoints.IdRoute " +
//            "LEFT JOIN Locations ON RoutePoints.IdLocation = Locations.IdLocation " +
//            "WHERE Routes.IdRoute IN (:idRoute)")
//    public abstract Cursor getPlantsIdByRouteId(int idRoute);

    @Query("SELECT Routes.IdRoute, Length, Time, MapImage, MapImageDetailed, Name, Description " +
            "FROM Routes LEFT JOIN RoutePoints ON Routes.IdRoute = RoutePoints.IdRoute " +
            "LEFT JOIN RoutesTranslations ON Routes.IdRoute = RoutesTranslations.IdRoute " +
            "WHERE Routes.IdRoute IN (:id) AND TranslationCode IN (:translationCode)")
    public abstract  Route getById(int id, String translationCode);

    @Query("SELECT Routes.IdRoute, Length, Time, MapImage, MapImageDetailed, RoutesTranslations.Name, Description " +
            "FROM Routes LEFT JOIN RoutesTranslations ON Routes.IdRoute = RoutesTranslations.IdRoute " +
            "WHERE RoutesTranslations.Name IN (:name) AND TranslationCode IN (:translationCode) LIMIT 1")
    public abstract  Route getByName(String name, String translationCode);


}
