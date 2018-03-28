package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Repository.RouteRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Route;

/**
 * Created by Michal on 28.03.2018.
 */

public class RouteDetailViewModel {

    List<Route> mRoutes;
    RouteRepository mRouteRepo;

    public RouteDetailViewModel() {
        mRouteRepo = new RouteRepository();
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
}
