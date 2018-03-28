package arboretum.arboretumwojslawice.ViewModel;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Model.Repository.RouteRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Route;

/**
 * Created by Michal on 24.03.2018.
 */

public class RouteViewModel {
    List<Route> mRoutes;
    RouteRepository mRouteRepo;

    public RouteViewModel() {
        mRouteRepo = new RouteRepository();
    }

    public List<Route> getData() {
        mRoutes = mRouteRepo.getRoutesForMichal();
        return mRoutes;
    }
}
