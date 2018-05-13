package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;
import arboretum.arboretumwojslawice.Model.Repository.RouteRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.Model.businessentity.PointOnRoute;
import arboretum.arboretumwojslawice.Model.businessentity.Route;

/**
 * Created by Michal on 28.03.2018.
 */

public class RouteDetailViewModel {

    List<Plant> mPlants;
    List<Route> mRoutes;
    List<PointOnRoute> mPointOnRoutes;
    @Inject
    protected RouteRepository routeRepository;
    @Inject
    protected PlantRepository plantRepository;

    @Inject
    public RouteDetailViewModel() { }

    public List<Route> getAllRoutes() {
        return routeRepository.getAllRoutes();
    }

    public Route getById(int routeId) {
        return routeRepository.getById(routeId);
    }



    /* I to też niech ktoś skasuje, no proszę... :D */

    public Route getRouteById(int route_id) {
        getRoutes();
        return mRoutes.get(route_id);
    }

    public List<Route> getRoutes() {
        mRoutes = routeRepository.getRoutesForMichal();
        return mRoutes;
    }

    public List<PointOnRoute> getRoutePointsForRoute1() {
        mPointOnRoutes = routeRepository.getRoutePointsByRouteIdForRoutes1();
        return mPointOnRoutes;
    }

    public List<Plant> getPlants() {
        mPlants = plantRepository.getPlantsForMichal();
        return mPlants;
    }
}
