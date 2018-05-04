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



    public List<Route> getRoutesForMichal() {
        List<Route> routes = new ArrayList<>();
        Route route = new Route(0, 3.5, 215, "route_full_1", "route_1", "Trasa dla seniorów", "Trasa prowadzi po niewielkim obszarze Arboretum. Ścieżki są płaskie, ale prowadzi przez malownicze tereny. Czas przejścia trasy był liczony dla wolnego spaceru, dlatego wynosi trzy godziny. Polecamy ją zatem osobom starszym, które mają już za słabe stawy i kolana do długiego zwiedzania.");
        routes.add(route);
        route = new Route(1, 1.5, 315, "route_full_2", "route_2", "Dla rodzin z dziećmi", "Traska przechodzi przez wiele terenów zakrzaczonych, gdzie wasze małe urwisy będą się wam mogły zgubić w lesie i będziecie mieli święty spokój chociaż przez chwilę, by móc podziwiać zbiory roślin w naszym Arboretum.");
        routes.add(route);
        route = new Route(2, 7.2, 420, "route_full_3", "route_3", "Trasa idealna dla poszukiwaczy przygód", "W sumie nie mam pomysłu na opis, ale jakoś tak mi ci poszukiwacze przygód wpadli, jak dałam jako obrazek ikonkę mapki.... :D");
        routes.add(route);
        route = new Route(3, 5., 200, "route_full_4", "route_4", "Trasa rododendronowa", "W końcu czy nie po to każdy przyjeżdża do Arboretum? Z powodu sławnych rododendronów?\n(teraz się zastanawiam czy zrobi się ta spacja czy nie)\nNIEEEE - my tam przyjedziemy jedynie służbowo :(");
        routes.add(route);
        route = new Route(4, 3.1, 130, "ic_more", "icons8_more", "Sprinterska", "Nie masz zbyt wiele czasu, bo w brzuszku już burczy? Trasa w sam raz dla Ciebie! Oferuje krótki spacer po najciekawszych okazach w Arboretum, kończąc Twoją przygodę w naszej restauracji ;)");
        routes.add(route);
        route = new Route(5, 8.9, 530, "ic_map", "icons8_map", "W poszukiwaniu ciekawych roślinek", "Za pomocą naszej super funkcjonalności, jaką jest QR kod, już nie będziesz musiał chodzić po Arboretum z Atlasem roślin! (jakbyś kiedykolwiek w ogóle wpadł na pomysł, że można tak robić) Sciągnij naszą super aplikację już dziś! (oł jeee).");
        routes.add(route);
        route = new Route(6, 13., 540, "ic_heart", "icons8_heart", "Całodzienna trasa dla cieszących się życiem", "To jest trasa dla prawdziwych kozaków! " + System.getProperty("line.separator") + "(zastanawiam się czy zrobił się enter normalnie - bo to taki kozacki enter, prawie tak kozacki jak ta trasa :D). aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        routes.add(route);

        return routes;
    }

    //na potrzeby prezentacji działania szczegółów tras (bez działającej bazy danych)
    public List<PointOnRoute> getRoutePointsByRouteIdForRoutes1() {
        mPlantRepo = new PlantRepository();
        List <PointOnRoute> rotesPoints = new ArrayList<>();
        PointOnRoute point = new PointOnRoute(0, mPlantRepo.getPlantsForMichal().get(0), 50.711856, 16.858557);
        rotesPoints.add(point);
        point = new PointOnRoute(1, mPlantRepo.getPlantsForMichal().get(2), 50.711844, 16.858560);
        rotesPoints.add(point);
        point = new PointOnRoute(2, mPlantRepo.getPlantsForMichal().get(3), 50.711865, 16.858543);
        rotesPoints.add(point);
        point = new PointOnRoute(3, mPlantRepo.getPlantsForMichal().get(6), 50.711866, 16.858553);
        rotesPoints.add(point);

        return rotesPoints;
    }

}
