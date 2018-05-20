package arboretum.arboretumwojslawice.ViewModel;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;

public class QRCodeViewModel {

    @Inject
    protected PlantRepository plantRepo;

    @Inject
    public QRCodeViewModel() {}

    public Plant getById(int id) {
        return plantRepo.getById(id);
    }
}
