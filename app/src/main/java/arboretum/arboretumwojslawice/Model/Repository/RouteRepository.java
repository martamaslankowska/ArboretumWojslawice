package arboretum.arboretumwojslawice.Model.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.DAO.LocationDao;
import arboretum.arboretumwojslawice.Model.DAO.RouteDao;
import arboretum.arboretumwojslawice.Model.DAO.RouteMapCoordDao;
import arboretum.arboretumwojslawice.Model.DAO.RoutePointDao;
import arboretum.arboretumwojslawice.Model.DAO.RouteTranslationDao;
import arboretum.arboretumwojslawice.Model.businessentity.PointOnRoute;
import arboretum.arboretumwojslawice.Model.businessentity.Route;
import arboretum.arboretumwojslawice.R;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Komputer on 2018-03-25.
 */

public class RouteRepository extends BaseRepository {

    @Inject
    RouteDao routeDao;
    @Inject
    RouteTranslationDao routeTranslationDao;
    @Inject
    RoutePointDao routePointDao;
    @Inject
    LocationDao locationDao;
    @Inject
    RouteMapCoordDao routeMapCoordDao;

    @Inject
    protected PlantRepository mPlantRepo;

    @Inject
    public RouteRepository() {}


    public List<Route> getAllRoutes() {
        return routeDao.getAll(languageCode);
    }

    public Route getById(int id) {
        return routeDao.getById(id, languageCode);
    }

    public Route getByName(String name) {
        return routeDao.getByName(name, languageCode);
    }

    public List<PointOnRoute> getRoutePointsByRouteId(int idRoute) {
        return routeDao.getRoutePointsByRouteId(idRoute);
    }

    public List<PointOnRoute> getHighlightedRoutePointsByRouteId(int idRoute) {
        return routeDao.getHighlightedRoutePointsByRouteId(idRoute);
    }
}
