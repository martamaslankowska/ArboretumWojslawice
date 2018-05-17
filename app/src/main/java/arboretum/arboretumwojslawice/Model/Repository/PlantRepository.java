package arboretum.arboretumwojslawice.Model.Repository;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.DAO.GenusDao;
import arboretum.arboretumwojslawice.Model.DAO.LocationDao;
import arboretum.arboretumwojslawice.Model.DAO.PlantDao;
import arboretum.arboretumwojslawice.Model.DAO.PlantTranslationDao;
import arboretum.arboretumwojslawice.Model.DAO.SpeciesDao;
import arboretum.arboretumwojslawice.Model.Entity.PlantEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Location;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;

import java.util.ArrayList;

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
    LocationDao locationDao;


    @Inject
    public PlantRepository() {}

    public List<Plant> getAllPlants() {
        return plantDao.getAll(languageCode);
    }

    public Plant getById(int id) {
        return plantDao.getById(id, languageCode);
    }

    public boolean setFavouriteById(int id) {
        Plant mPlant = getById(id);
        if (mPlant.getFavourite()) {
            mPlant.setFavourite(false);
        }
        else {
            mPlant.setFavourite(true);
        }
        PlantEntity newPlant = new PlantEntity(mPlant.getIdPlant(), mPlant.getLatinName(), speciesDao.getSpeciesId(mPlant.getGenusName(), mPlant.getSpeciesName()),
                mPlant.getKind(), mPlant.getImage(), mPlant.getSeasonBegin(), mPlant.getSeasonEnd(), mPlant.getFavourite());
        plantDao.update(newPlant);
        if (mPlant.getFavourite()) {
            return true;
        }
        else {
            return false;
        }
    }

    public List<Plant> getAllByKind(int kind) {
        return plantDao.getAllByKindName(kind, languageCode);
    }

    public List<Location> getLocationsByPlantId(int idPlant) {
        return plantDao.getLocationsByPlantId(idPlant);
    }

    public List<Plant> getFavouritePlants() {
        return plantDao.getFavouritePlants(languageCode);
    }

    public List<Plant> getAllSeasonPlants(int date) {
        return plantDao.getAllSeasonPlants(date, languageCode);
    }
}
