package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Location;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;

/**
 * Created by weronika on 04.04.2018.
 */

public class PlantViewModel {

    @Inject
    protected PlantRepository plantRepo;

    @Inject
    public PlantViewModel(){ }

    public List<Plant> getAllByKind(int kind) {
        return plantRepo.getAllByKind(kind);
    }

    public boolean setFavourite(int id) {
        return plantRepo.setFavouriteById(id);
    }

    public Plant getById(int id) {
        return plantRepo.getById(id);
    }

    public List<Location> getPlantLocations(int id) {
        return plantRepo.getLocationsByPlantId(id);
    }

}
