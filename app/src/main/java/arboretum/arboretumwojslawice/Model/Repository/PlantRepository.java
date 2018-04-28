package arboretum.arboretumwojslawice.Model.Repository;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.DAO.GenusDao;
import arboretum.arboretumwojslawice.Model.DAO.LocationDao;
import arboretum.arboretumwojslawice.Model.DAO.PlantDao;
import arboretum.arboretumwojslawice.Model.DAO.PlantTranslationDao;
import arboretum.arboretumwojslawice.Model.DAO.SpeciesDao;
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

    public  Plant getById(int id) {
        return plantDao.getById(id, languageCode);
    }

    public List<Plant> getAllByKindName(int kind) {
        return plantDao.getAllByKindName(kind, languageCode);
    }

    public List<Location> getLocationsByPlantId(int idPlant) {
        return plantDao.getLocationsByPlantId(idPlant);
    }


    public List<Plant> getPlantsForMichal() {
        List<Plant> plants = new ArrayList<>();
        Plant plant = new Plant(1, "Rodzaj_1", "Gatunek_1", "latinlatin",
                "Kwiatuszek1", 1, "flower1",20180509, 20180810,
                "Opis pierwszego pięknego kwiatuszka", false);
        plants.add(plant);
        plant = new Plant(2, "Rodzaj_2", "Gatunek_2", "rose",
                "Kwiatuszek2", 2, "flower2",20181209, 20181210,
                "Opis drugiego pięknego kwiatuszka", false);
        plants.add(plant);
        plant = new Plant(3, "Rodzaj_3", "Gatunek_3", "tulipanix",
                "Kwiatuszek3", 2, "flower3",20181209, 20181210,
                "Opis trzeciego pięknego kwiatuszka", false);
        plants.add(plant);
        plant = new Plant(4, "Rodzaj_4", "Gatunek_4", "kwiacix",
                "Kwiatuszek4", 3, "flower2",20181209, 20181210,
                "Opis czwartego pięknego kwiatuszka", false);
        plants.add(plant);
        plant = new Plant(5, "Rodzaj_5", "Gatunek_5", "drzewkox",
                "Kwiatuszek5", 3, "flower2",20181209, 20181210,
                "Opis piątego pięknego kwiatuszka", false);
        plants.add(plant);
        plant = new Plant(6, "Rodzaj_6", "Gatunek_6", "lipix",
                "Kwiatuszek6", 4, "flower3",20181209, 20181210,
                "Opis szóstego pięknego kwiatuszka", false);
        plants.add(plant);
        plant = new Plant(7, "Rodzaj_7", "Gatunek_7", "rododendronix",
                "Kwiatuszek7", 2, "flower1",20181209, 20181210,
                "Opis siódmego pięknego kwiatuszka", false);
        plants.add(plant);
        plant = new Plant(8, "Rodzaj_8", "Gatunek_8", "kwiatuszex",
                "Kwiatuszek8", 3, "flower3",20181209, 20181210,
                "Opis ósmego pięknego kwiatuszka", false);
        plants.add(plant);
        plant = new Plant(9, "Rodzaj_9", "Gatunek_9", "lilielix",
                "Kwiatuszek9", 2, "flower8",20181209, 20181210,
                "Opis dziewiątego pięknego kwiatuszka", false);
        plants.add(plant);
        plant = new Plant(10, "Żółtakowate", "Lilia", "Xanthorrhoeaceae",
                "Hemerocallis", 3, "flower3",20181209, 20181210,
                "Wojsławicki zbiór liliowców – pięknych bylin, kwitnących w czerwcu i lipcu, nazywanych „kwiatami jednego dnia” – jest największą i najlepiej prowadzoną kolekcją tego typu roślin w Polsce oraz jedną z największych w Europie. Kolekcja rozwijana jest systematycznie od 2002 roku – corocznie sadzonych jest około 200 nowych odmian liliowców. Obecnie zbiór ten obejmuje prawie 4 tysiące gatunków i odmian. W 2011 roku ta wzorcowa kolekcja została pozytywnie zweryfikowana przez komisję powołaną przez Polskie Towarzystwo Ogrodów Botanicznych i uzyskała status Kolekcji Narodowej. W Arboretum została wyodrębniona kolekcja gatunków oraz następujące grupy odmian: • liliowce miniaturowe • pełnokwiatowe • pachnące • pajęcze • nagrodzone amerykańskim medalem Stouta • wyhodowane w szkółce Siloam w Stanach Zjednoczonych • odmiany z serii Broadway wyhodowane na Florydzie liliowce polskiej hodowli, a wśród nich wyhodowane przez: – brata Stefana Franczaka, – Artura Jasińskiego, – Jerzego Byczyńskiego, – Leopolda Kurka, – Stanisława Achramowicza, – Grażyny Świątkowskiej, – i innych. W 2013 nasz kolekcja uzyskała prestiżowy certyfikat ogrodu pokazowego Amerykańskiego Towarzystwa Liliowcowego. Jesteśmy z tego dumni. W tym roku zostaliśmy także europejską stacją oceny odmian liliowców. Zainteresowanych tą wspaniała grupą roślin zapraszamy na coroczne spotkania liliowcowych entuzjastów „HEMEROmania”, które odbywa się w Wojsławicach w połowie lipca ", false);
        plants.add(plant);

        return plants;
    }
}
