package arboretum.arboretumwojslawice.Model.Repository;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.DAO.RestaurantDao;
import arboretum.arboretumwojslawice.Model.businessentity.Restaurant;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Komputer on 2018-03-25.
 */

public class RestaurantRepository extends BaseRepository {

    private RestaurantDao restaurantDao;

    @Inject
    public RestaurantRepository(RestaurantDao RestaurantDao, Locale locale) {
        this.restaurantDao = RestaurantDao;
        this.locale = locale;
    }

    public Maybe<List<Restaurant>> getAllRestaurants() {
        return restaurantDao.getAll();
    }

    public Single<Restaurant> getById(int id) {
        return restaurantDao.getById(id);
    }

    public Single<Restaurant> getByName(String name) {
        return restaurantDao.getByName(name);
    }

    public Maybe<List<Restaurant>> getAllBetterThan(double rating) {
        return restaurantDao.getAllBetterThan(rating);
    }
    
}
