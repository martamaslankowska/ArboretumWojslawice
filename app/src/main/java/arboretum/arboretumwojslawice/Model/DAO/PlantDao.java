package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.PlantEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Location;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import io.reactivex.Maybe;
import io.reactivex.Single;


/**
 * Created by Komputer on 2018-03-23.
 */


@Dao
public abstract class PlantDao extends BaseDao<PlantEntity> {

    @Query("SELECT Plants.IdPlant, GenusName, SpeciesName, Plants.LatinName, PlantsTranslations.Name, KindsTranslations.Name, Image, SeasonBegin, SeasonEnd, Description, Favourite " +
            "FROM Plants LEFT JOIN Species ON Plants.IdSpecies = Species.IdSpecies " +
            "LEFT JOIN Genus ON Species.GenusName = Genus.Name " +
            "LEFT JOIN Kinds ON Plants.IdKind = Kinds.IdKind " +
            "LEFT JOIN KindsTranslations ON Kinds.IdKind = KindsTranslations.IdKind " +
//            "LEFT JOIN Locations ON Plants.IdPlant = Locations.IdPlant " +
            "LEFT JOIN PlantsTranslations ON Plants.IdPlant = PlantsTranslations.IdPlant " +
            "WHERE PlantsTranslations.TranslationCode IN (:translationCode) AND KindsTranslations.TranslationCode IN (:translationCode)")
    public abstract List<Plant> getAll(String translationCode);

    @Query("SELECT IdLocation, X, Y " +
            "FROM Plants INNER JOIN Locations ON Plants.IdPlant = Locations.IdPlant " +
            "WHERE Plants.IdPlant IN (:idPlant)")
    public abstract List<Location> getLocationsByPlantId(int idPlant);

    @Query("SELECT Plants.IdPlant, GenusName, SpeciesName, Plants.LatinName, PlantsTranslations.Name, KindsTranslations.Name, Image, SeasonBegin, SeasonEnd, Description, Favourite " +
            "FROM Plants LEFT JOIN Species ON Plants.IdSpecies = Species.IdSpecies " +
            "LEFT JOIN Genus ON Species.GenusName = Genus.Name " +
            "LEFT JOIN Kinds ON Plants.IdKind = Kinds.IdKind " +
            "LEFT JOIN KindsTranslations ON Kinds.IdKind = KindsTranslations.IdKind " +
            "LEFT JOIN PlantsTranslations ON Plants.IdPlant = PlantsTranslations.IdPlant " +
            "WHERE Plants.IdPlant IN (:idPlant) AND PlantsTranslations.TranslationCode IN (:translationCode) AND KindsTranslations.TranslationCode IN (:translationCode)")
    public abstract  Plant getById(int idPlant, String translationCode);

    @Query("SELECT Plants.IdPlant, GenusName, SpeciesName, Plants.LatinName, PlantsTranslations.Name, KindsTranslations.Name, Image, SeasonBegin, SeasonEnd, Description, Favourite " +
            "FROM Plants LEFT JOIN Species ON Plants.IdSpecies = Species.IdSpecies " +
            "LEFT JOIN Genus ON Species.GenusName = Genus.Name " +
            "LEFT JOIN Kinds ON Plants.IdKind = Kinds.IdKind " +
            "LEFT JOIN KindsTranslations ON Kinds.IdKind = KindsTranslations.IdKind " +
            "LEFT JOIN PlantsTranslations ON Plants.IdPlant = PlantsTranslations.IdPlant " +
            "WHERE KindsTranslations.Name IN (:kindName) AND PlantsTranslations.TranslationCode IN (:translationCode) AND KindsTranslations.TranslationCode IN (:translationCode)")
    public abstract List<Plant> getAllByKindName(String kindName, String translationCode);



}
