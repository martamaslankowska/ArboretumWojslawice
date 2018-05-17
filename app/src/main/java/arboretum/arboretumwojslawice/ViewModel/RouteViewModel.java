package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.RouteRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Route;

/**
 * Created by Michal on 24.03.2018.
 */

public class RouteViewModel {

    @Inject
    protected RouteRepository routeRepository;

    @Inject
    public RouteViewModel() { }

    public List<Route> getAllRoutes() {
        return routeRepository.getAllRoutes();
    }

    public Route getById(int routeId) {
        return routeRepository.getById(routeId);
    }

}
