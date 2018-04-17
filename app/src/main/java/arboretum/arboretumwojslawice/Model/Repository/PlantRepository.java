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


    public List<Plant> getPlantsForMichal() {
        List<Plant> plants = new ArrayList<>();
        Plant plant = new Plant(1, "Rodzaj_1", "Gatunek_1", "latinlatin",
                "Kwiatuszek1", 1, R.drawable.flower1,20180509, 20180810,
                "Opis pierwszego pięknego kwiatuszka");
        plants.add(plant);
        plant = new Plant(2, "Rodzaj_2", "Gatunek_2", "rose",
                "Kwiatuszek2", 2, R.drawable.flower2,20181209, 20181210,
                "Opis drugiego pięknego kwiatuszka");
        plants.add(plant);
        plant = new Plant(3, "Rodzaj_3", "Gatunek_3", "tulipanix",
                "Kwiatuszek3", 2, R.drawable.flower3,20181209, 20181210,
                "Opis trzeciego pięknego kwiatuszka");
        plants.add(plant);
        plant = new Plant(4, "Rodzaj_4", "Gatunek_4", "kwiacix",
                "Kwiatuszek4", 3, R.drawable.flower2,20181209, 20181210,
                "Opis czwartego pięknego kwiatuszka");
        plants.add(plant);
        plant = new Plant(5, "Rodzaj_5", "Gatunek_5", "drzewkox",
                "Kwiatuszek5", 3, R.drawable.flower2,20181209, 20181210,
                "Opis piątego pięknego kwiatuszka");
        plants.add(plant);
        plant = new Plant(6, "Rodzaj_6", "Gatunek_6", "lipix",
                "Kwiatuszek6", 4, R.drawable.flower3,20181209, 20181210,
                "Opis szóstego pięknego kwiatuszka");
        plants.add(plant);
        plant = new Plant(7, "Rodzaj_7", "Gatunek_7", "rododendronix",
                "Kwiatuszek7", 2, R.drawable.flower1,20181209, 20181210,
                "Opis siódmego pięknego kwiatuszka");
        plants.add(plant);
        plant = new Plant(8, "Rodzaj_8", "Gatunek_8", "kwiatuszex",
                "Kwiatuszek8", 3, R.drawable.flower1,20181209, 20181210,
                "Opis ósmego pięknego kwiatuszka");
        plants.add(plant);
        plant = new Plant(9, "Rodzaj_9", "Gatunek_9", "lilielix",
                "Kwiatuszek9", 2, R.drawable.flower2,20181209, 20181210,
                "Opis dziewiątego pięknego kwiatuszka");
        plants.add(plant);
        plant = new Plant(10, "Rodzaj_10", "Gatunek_10", "gerberix",
                "Kwiatuszek10", 3, R.drawable.flower3,20181209, 20181210,
                "Opis dzieisątego pięknego kwiatuszka");
        plants.add(plant);

        return plants;
    }
}
