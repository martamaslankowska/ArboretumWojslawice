package arboretum.arboretumwojslawice.ViewModel;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;

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

    // return one plant founded by id
    public Plant getPlantById(int plant_id) {
        getData();
        for(int i = 0; i < mPlant.size(); i++) {
            if(mPlant.get(i).getIdPlant() == plant_id) {
                return mPlant.get(i);
            }
        }
        return mPlant.get(plant_id);
    }

    public List<Plant> getPlantsFromTab(int tab_id) {
        List<Plant> tab_plants = new ArrayList<Plant>();
        getData();
        for(int i = 0 ; i < mPlant.size(); i++) {
            if(mPlant.get(i).getKind() == tab_id) {
                tab_plants.add(mPlant.get(i));
            }
        }
        return tab_plants;
    }
}
