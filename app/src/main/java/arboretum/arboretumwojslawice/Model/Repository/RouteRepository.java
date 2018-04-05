package arboretum.arboretumwojslawice.Model.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.DAO.LocationDao;
import arboretum.arboretumwojslawice.Model.DAO.RouteDao;
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
    public RouteRepository() {}


    public List<Route> getAllRoutes() {
        return routeDao.getAll();
    }

    public  Route getById(int id) {
        return routeDao.getById(id);
    }

    public  Route getByName(String name) {
        return routeDao.getByName(name);
    }

    public List<PointOnRoute> getRoutePointsByRouteId(int idRoute) {
        return routeDao.getRoutePointsByRouteId(idRoute);
    }



    public List<Route> getRoutesForMichal() {
        List<Route> routes = new ArrayList<>();
        Route route = new Route(1, 3.5, 215, R.drawable.icons8_more, "Traska dla emerytów", "To jest własnie taka jakaś lajtowa traska, bo pomimo tego, że jej przejście to poad dwie godziny, to ogarnia niewielki zakres Arboretum. Polecamy ją zatem osobom starszym, które mają już za słabe stawy i kolana do długiego zwiedzania");
        routes.add(route);
        route = new Route(2, 1.5, 315, R.drawable.icons8_heart, "Dla rodzin z dziećmi", "Traska przechodzi przez wiele terenów zakrzaczonych, gdzie wasze małe urwisy będą się wam mogły zgubić w lesie i będziecie mieli święty spokój chociaż przez chwilę, by móc podziwiać zbiory roślin w naszym Arboretum");
        routes.add(route);
        route = new Route(3, 7.2, 420, R.drawable.icons8_map, "Trasa idealna dla poszukiwaczy przygód", "W sumie nie mam pomysłu na opis, ale jakoś tak mi ci poszukiwacze przygód wpadli, jak dałam jako obrazek ikonkę mapki.... :D");
        routes.add(route);
        route = new Route(4, 5., 200, R.drawable.ic_home_black_24dp, "Trasa rododendronowa", "W końcu czy nie po to każdy przyjeżdża do Arboretum? Z powodu sławnych rododendronów?\n\n(teraz się zastanawiam czy zrobi się ta spacja czy nie)\n\nNIEEEE - my tam przyjedziemy jedynie służbowo :(");
        routes.add(route);
        route = new Route(5, 3.1, 130, R.drawable.icons8_more, "Sprinterska", "Nie masz zbyt wiele czasu, bo w brzuszku już burczy? Trasa w sam raz dla Ciebie! Oferuje krótki spacer po najciekawszych okazach w Arboretum, kończąc Twoją przygodę w naszej restauracji ;)");
        routes.add(route);
        route = new Route(6, 8.9, 530, R.drawable.icons8_map, "W poszukiwaniu ciekawych roślinek", "Za pomocą naszej super funkcjonalności, jaką jest QR kod, już nie będziesz musiał chodzić po Arboretum z Atlasem roślin! (jakbyś kiedykolwiek w ogóle wpadł na pomysł, że można tak robić) Sciągnij naszą super aplikację już dziś! (oł jeee)");
        routes.add(route);
        route = new Route(7, 13., 540, R.drawable.icons8_heart, "Całodzienna trasa dla cieszących się życiem", "To jest trasa dla prawdziwych kozaków! " + System.getProperty("line.separator") + "(zastanawiam się czy zrobił się enter normalnie - bo to taki kozacki enter, prawie tak kozacki jak ta trasa :D) ");
        routes.add(route);

        return routes;
    }

}
