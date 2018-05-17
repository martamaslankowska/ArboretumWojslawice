package arboretum.arboretumwojslawice.ViewModel;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;

/**
 * Created by weronika on 04.04.2018.
 */

public class PlantDetailViewModel {

    @Inject
    protected PlantRepository plantRepo;

    @Inject
    public PlantDetailViewModel() { }

    public Plant getById(int id) {
        return plantRepo.getById(id);
    }

}
