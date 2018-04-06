package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;
import arboretum.arboretumwojslawice.Model.Repository.RouteRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.Model.businessentity.Route;

/**
 * Created by weronika on 04.04.2018.
 */

public class PlantViewModel {
    List<Plant> mPlant;
    PlantRepository mPlantRepo;

    public PlantViewModel() {
        mPlantRepo = new PlantRepository();
    }

    public List<Plant> getData() {
        mPlant = mPlantRepo.getPlantsForMichal();
        return mPlant;
    }
}