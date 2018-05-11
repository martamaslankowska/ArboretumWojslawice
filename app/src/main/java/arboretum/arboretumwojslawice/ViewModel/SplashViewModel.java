package arboretum.arboretumwojslawice.ViewModel;

import javax.inject.Inject;
import arboretum.arboretumwojslawice.Model.Repository.AttractionRepository;
import arboretum.arboretumwojslawice.Model.Repository.EventRepository;
import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;

public class SplashViewModel {

    @Inject
    AttractionRepository attractionRepository;
    @Inject
    PlantRepository plantRepository;
    @Inject
    EventRepository eventRepository;

    @Inject
    SplashViewModel() {

    }

}
