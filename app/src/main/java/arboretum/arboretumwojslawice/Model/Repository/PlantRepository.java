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
        return plantDao.getAll(languageCode);
    }

    public  Plant getById(int id) {
        return plantDao.getById(id, languageCode);
    }

    public List<Plant> getByName(String name) {
        return plantDao.getAllByKindName(name, languageCode);
    }

    public List<Location> getLocationsByPlantId(int idPlant) {
        return plantDao.getLocationsByPlantId(idPlant);
    }


    public List<Plant> getPlantsForMichal(int n) {
        List<Plant> plants1 = new ArrayList<>();
        List<Plant> plants2 = new ArrayList<>();
        List<Plant> plants3 = new ArrayList<>();
        List<Plant> plants4 = new ArrayList<>();
        Plant route = new Plant("Kwiatek_1", 1, "Rodzaj_1", "Gatunek_1", R.drawable.flower1);
        plants1.add(route);
        plants3.add(route);
        route = new Plant("Kwiatek_2", 2, "Rodzaj_2", "Gatunek_2", R.drawable.flower2);
        plants1.add(route);
        plants4.add(route);
        route = new Plant("Kwiatek_3", 3, "Rodzaj_3", "Gatunek_3", R.drawable.flower1);
        plants1.add(route);
        plants2.add(route);
        route = new Plant("Kwiatek_4", 4, "Rodzaj_4", "Gatunek_4", R.drawable.flower3);
        plants3.add(route);
        plants2.add(route);
        route = new Plant("Kwiatek_5", 5, "Rodzaj_5", "Gatunek_5", R.drawable.flower2);
        plants4.add(route);
        plants2.add(route);
        route = new Plant("Kwiatek_6", 6, "Rodzaj_6", "Gatunek_6", R.drawable.flower1);
        plants1.add(route);
        plants2.add(route);
        route = new Plant("Kwiatek_7", 7, "Rodzaj_7", "Gatunek_7", R.drawable.flower3);
        plants3.add(route);
        plants4.add(route);
        route = new Plant("Kwiatek_8", 8, "Rodzaj_8", "Gatunek_8", R.drawable.flower2);
        plants1.add(route);
        plants3.add(route);
        switch (n) {
            case 0:
                return plants1;
            case 1:
                return plants2;
            case 2:
                return plants3;
            case 3:
                return plants4;
        }
        return plants1;
    }



}
