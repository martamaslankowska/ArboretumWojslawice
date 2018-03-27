package arboretum.arboretumwojslawice.ViewModel;

import java.util.ArrayList;
import java.util.List;
import arboretum.arboretumwojslawice.Model.businessentity.Route;

/**
 * Created by Michal on 24.03.2018.
 */

public class RouteViewModel {
    List<Route> mRoutes;

    public void onCreate() {

    }

    public List<Route> getData() {
        mRoutes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mRoutes.add(new Route(i, "Trasa " + i, "Opis " + i));
        }
        return mRoutes;
    }
}
