package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

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
    RouteRepository mRouteRepo;
    PlantRepository mPlantRepo;
    PlantViewModel mPlantViewModel;

    public RouteDetailViewModel() {
        mRouteRepo = new RouteRepository();
        mPlantRepo = new PlantRepository();
        mPlantViewModel = new PlantViewModel();
    }

    public Route getRouteById(int route_id) {
        getRoutes();
        return mRoutes.get(route_id);
    }

    public List<Route> getRoutes() {
        mRoutes = mRouteRepo.getRoutesForMichal();
        return mRoutes;
    }

    public List<PointOnRoute> getRoutePointsForRoute1() {
        mPointOnRoutes = mRouteRepo.getRoutePointsByRouteIdForRoutes1();
        return mPointOnRoutes;
    }

    public List<Plant> getPlants() {
        mPlants = mPlantRepo.getPlantsForMichal();
        return mPlants;
    }
}
