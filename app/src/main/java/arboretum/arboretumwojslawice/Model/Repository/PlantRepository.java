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

    private PlantDao plantDao;
    private PlantTranslationDao plantTranslationDao;
    private GenusDao genusDao;
    private SpeciesDao speciesDao;
    private KindDao kindDao;
    private KindTranslationDao kindTranslationDao;
    private LocationDao locationDao;

    @Inject
    public PlantRepository(PlantDao plantDao, PlantTranslationDao plantTranslationDao, GenusDao genusDao, SpeciesDao speciesDao, KindDao kindDao, KindTranslationDao kindTranslationDao, LocationDao locationDao, Locale locale) {
        this.plantDao = plantDao;
        this.plantTranslationDao = plantTranslationDao;
        this.genusDao = genusDao;
        this.speciesDao = speciesDao;
        this.kindDao = kindDao;
        this.kindTranslationDao = kindTranslationDao;
        this.locationDao = locationDao;
        this.locale = locale;
    }

    public PlantRepository() {}

    public Maybe<List<Plant>> getAllPlants() {
        return plantDao.getAll();
    }

    public Single<Plant> getById(int id) {
        return plantDao.getById(id);
    }

    public Maybe<List<Plant>> getByName(String name) {
        return plantDao.getAllByKindName(name);
    }

    public Maybe<List<Location>> getLocationsByPlantId(int idPlant) {
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
