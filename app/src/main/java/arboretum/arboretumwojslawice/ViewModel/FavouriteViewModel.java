package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;

/**
 * Created by weronika on 06.05.2018.
 */

public class FavouriteViewModel {

    @Inject
    protected PlantRepository plantRepo;

    @Inject
    public FavouriteViewModel() {
    }
    
    public List<Plant> getAllFavourites() {
        return plantRepo.getFavouritePlants();
    }
}
