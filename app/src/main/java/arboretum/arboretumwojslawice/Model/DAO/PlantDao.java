package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.PlantEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import io.reactivex.Maybe;


/**
 * Created by Komputer on 2018-03-23.
 */


@Dao
abstract class PlantDao implements BaseDao<PlantEntity> {

    @Query("SELECT Plants.IdPlant, GenusName, SpeciesName, Plants.Name, Kinds.Name, Image, SeasonBegin, SeasonEnd, Description " +
            "FROM Plants LEFT JOIN Species ON Plants.IdSpecies = Species.IdSpecies " +
            "LEFT JOIN Genus ON Species.GenusName = Genus.Name " +
            "LEFT JOIN Kinds ON Plants.IdKind = Kinds.IdKind " +
            "LEFT JOIN KindsTranslations ON Kinds.IdKind = KindsTranslations.IdKind " +
//            "LEFT JOIN Locations ON Plants.IdPlant = Locations.IdPlant " +
            "LEFT JOIN PlantsTranslations ON Plants.IdPlant = PlantsTranslations.IdPlant")
    abstract Maybe<List<Plant>> getAll();

    @Query("SELECT IdLocation, X, Y " +
            "FROM Plants INNER JOIN Locations ON Plants.IdPlant = Locations.IdLocation " +
            "WHERE IdPlant IN (:idPlant)")
    abstract Maybe<List<Plant.Location>> getLocationsByPlantId(int idPlant);

    @Query("SELECT Plants.IdPlant, GenusName, SpeciesName, Plants.Name, Kinds.Name, Image, SeasonBegin, SeasonEnd, Description " +
            "FROM Plants LEFT JOIN Species ON Plants.IdSpecies = Species.IdSpecies " +
            "LEFT JOIN Genus ON Species.GenusName = Genus.Name " +
            "LEFT JOIN Kinds ON Plants.IdKind = Kinds.IdKind " +
            "LEFT JOIN KindsTranslations ON Kinds.IdKind = KindsTranslations.IdKind " +
            "LEFT JOIN PlantsTranslations ON Plants.IdPlant = PlantsTranslations.IdPlant " +
            "WHERE IdPlant IN (:idPlant)")
    abstract Plant getById(int idPlant);

    @Query("SELECT Plants.IdPlant, GenusName, SpeciesName, Plants.Name, Kinds.Name, Image, SeasonBegin, SeasonEnd, Description " +
            "FROM Plants LEFT JOIN Species ON Plants.IdSpecies = Species.IdSpecies " +
            "LEFT JOIN Genus ON Species.GenusName = Genus.Name " +
            "LEFT JOIN Kinds ON Plants.IdKind = Kinds.IdKind " +
            "LEFT JOIN KindsTranslations ON Kinds.IdKind = KindsTranslations.IdKind " +
            "LEFT JOIN PlantsTranslations ON Plants.IdPlant = PlantsTranslations.IdPlant " +
            "WHERE Kinds.Name IN (:kindName)")
    abstract Maybe<List<Plant>> getAllByKindName(String kindName);



}
