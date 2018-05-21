package arboretum.arboretumwojslawice.Model.DAO;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.RouteEntity;
import arboretum.arboretumwojslawice.Model.Entity.RouteMapCoordEntity;
import arboretum.arboretumwojslawice.Model.businessentity.PointOnRoute;
import arboretum.arboretumwojslawice.Model.businessentity.RouteMapCoord;

@Dao
public abstract class RouteMapCoordDao extends BaseDao<RouteMapCoordEntity> {

    @Query("SELECT IdRoute, MapImage, MinLat, MaxLat, MinLon, MaxLon " +
            "FROM RouteMapCoords " +
            "WHERE IdRoute IN (:idRoute)")
    public abstract RouteMapCoord getRouteMapCoordByRouteId(int idRoute);

//    @Query("SELECT MapImage " +
//            "FROM RouteMapCoords " +
//            "WHERE IdRoute IN (:idRoute) LIMIT 1")
//    public abstract String getRouteMapByRouteId(int idRoute);

}
