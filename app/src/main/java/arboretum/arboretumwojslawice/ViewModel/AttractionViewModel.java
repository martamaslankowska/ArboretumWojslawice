package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.AttractionRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Attraction;

public class AttractionViewModel {

    @Inject
    AttractionRepository attractionRepo;

    @Inject
    AttractionViewModel() {}

    public List<Attraction> getAll() {
        return attractionRepo.getAllAttractions();
    }
}
