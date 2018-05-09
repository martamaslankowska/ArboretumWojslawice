package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.RestaurantRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Restaurant;

public class RestaurantViewModel {

    @Inject
    RestaurantRepository restaurantRepo;

    @Inject
    RestaurantViewModel() {}

    public List<Restaurant> getAll() {
        return restaurantRepo.getAllRestaurants();
    }
}
