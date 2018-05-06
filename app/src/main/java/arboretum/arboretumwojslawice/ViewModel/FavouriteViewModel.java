package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;

/**
 * Created by weronika on 06.05.2018.
 */

public class FavouriteViewModel {
    List<Plant> mPlant;

    @Inject
    protected PlantRepository mPlantRepo;

    @Inject
    public FavouriteViewModel(){ }

    public List<Plant> getData() {
        mPlant = mPlantRepo.getPlantsForMichal();
        return mPlant;
    }

    public Plant getPlantById(int plant_id) {
        getData();
        for(int i = 0; i < mPlant.size(); i++) {
            if(mPlant.get(i).getIdPlant() == plant_id) {
                return mPlant.get(i);
            }
        }
        return mPlant.get(plant_id);
    }
}
