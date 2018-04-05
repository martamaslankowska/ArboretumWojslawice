package arboretum.arboretumwojslawice.Model.Repository;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.DAO.GenusDao;
import arboretum.arboretumwojslawice.Model.DAO.KindDao;
import arboretum.arboretumwojslawice.Model.DAO.KindTranslationDao;
import arboretum.arboretumwojslawice.Model.DAO.LocationDao;
import arboretum.arboretumwojslawice.Model.DAO.PlantDao;
import arboretum.arboretumwojslawice.Model.DAO.PlantTranslationDao;
import arboretum.arboretumwojslawice.Model.DAO.SpeciesDao;
import arboretum.arboretumwojslawice.Model.businessentity.Location;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;

/**
 * Created by Komputer on 2018-03-24.
 */

public class PlantRepository extends  BaseRepository {

    @Inject
    PlantDao plantDao;
    @Inject
    PlantTranslationDao plantTranslationDao;
    @Inject
    GenusDao genusDao;
    @Inject
    SpeciesDao speciesDao;
    @Inject
    KindDao kindDao;
    @Inject
    KindTranslationDao kindTranslationDao;
    @Inject
    LocationDao locationDao;


    @Inject
    public PlantRepository() {}


    public List<Plant> getAllPlants() {
        return plantDao.getAll();
    }

    public  Plant getById(int id) {
        return plantDao.getById(id);
    }

    public List<Plant> getByName(String name) {
        return plantDao.getAllByKindName(name);
    }

    public List<Location> getLocationsByPlantId(int idPlant) {
        return plantDao.getLocationsByPlantId(idPlant);
    }


    public List<Plant> getPlantsForMichal() {
        List<Plant> plants1 = new ArrayList<>();
        Plant route = new Plant(1, "Rodzaj1", "Gatunek1", R.drawable.flower1);
        plants1.add(route);
        route = new Plant(2, "Rodzaj2", "Gatunek2", R.drawable.flower2);
        plants1.add(route);
        route = new Plant(3, "Rodzaj3", "Gatunek3", R.drawable.flower1);
        plants1.add(route);

        return plants1;
    }

}
