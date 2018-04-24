package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.RouteRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Route;

/**
 * Created by Michal on 24.03.2018.
 */

public class RouteViewModel {
    List<Route> mRoutes;
    RouteRepository mRouteRepo;

    @Inject
    public RouteViewModel() {
        mRouteRepo = new RouteRepository();
    }

    public List<Route> getData() {
        mRoutes = mRouteRepo.getRoutesForMichal();
        return mRoutes;
    }

    // return one plant founded by id
    public Route getRouteById(int plant_id) {
        getData();
        for(int i = 0; i < mRoutes.size(); i++) {
            if(mRoutes.get(i).getIdRoute() == plant_id) {
                return mRoutes.get(i);
            }
        }
        return mRoutes.get(plant_id);
    }
}
