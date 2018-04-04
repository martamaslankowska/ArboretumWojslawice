package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;
import arboretum.arboretumwojslawice.Model.Repository.RouteRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.Model.businessentity.Route;

/**
 * Created by Michal on 28.03.2018.
 */

public class RouteDetailViewModel {

    List<Plant> mPlants;
    List<Route> mRoutes;
    RouteRepository mRouteRepo;
    PlantRepository mPlantRepo;

    public RouteDetailViewModel() {
        mRouteRepo = new RouteRepository();
        mPlantRepo = new PlantRepository();
    }

    public Route getRouteById(int route_id)
    {
        getData();
        return mRoutes.get(route_id);
    }

    public List<Route> getData() {
        mRoutes = mRouteRepo.getRoutesForMichal();
        return mRoutes;
    }

    public List<Plant> getPlants() {
        mPlants = mPlantRepo.getPlantsForMichal();
        return mPlants;
    }
}
