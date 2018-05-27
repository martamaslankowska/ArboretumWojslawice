package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;

/**
 * Created by weronika on 27.05.2018.
 */

public class MainViewModel {

    @Inject
    protected PlantRepository plantRepo;

    @Inject
    public MainViewModel() {
    }

    public List<Plant> getAllFavourites() {
        return plantRepo.getFavouritePlants();
    }

    public Plant getById(int id) {
        return plantRepo.getById(id);
    }

    public void setAllFavourites(List<Plant> list) {
        for (Plant plant : list) {
            plantRepo.setFavouriteById(plant.getIdPlant());
        }
    }
}
