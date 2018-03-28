package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.PlantEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Location;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import io.reactivex.Maybe;


/**
 * Created by Komputer on 2018-03-23.
 */


@Dao
public abstract class PlantDao extends BaseDao<PlantEntity> {

    @Query("SELECT Plants.IdPlant, GenusName, SpeciesName, Plants.Name, KindsTranslations.Name, Image, SeasonBegin, SeasonEnd, Description " +
            "FROM Plants LEFT JOIN Species ON Plants.IdSpecies = Species.IdSpecies " +
            "LEFT JOIN Genus ON Species.GenusName = Genus.Name " +
            "LEFT JOIN Kinds ON Plants.IdKind = Kinds.IdKind " +
            "LEFT JOIN KindsTranslations ON Kinds.IdKind = KindsTranslations.IdKind " +
//            "LEFT JOIN Locations ON Plants.IdPlant = Locations.IdPlant " +
            "LEFT JOIN PlantsTranslations ON Plants.IdPlant = PlantsTranslations.IdPlant")
    public abstract Maybe<List<Plant>> getAll();

    @Query("SELECT IdLocation, X, Y " +
            "FROM Plants INNER JOIN Locations ON Plants.IdPlant = Locations.IdPlant " +
            "WHERE Plants.IdPlant IN (:idPlant)")
    public abstract Maybe<List<Location>> getLocationsByPlantId(int idPlant);

    @Query("SELECT Plants.IdPlant, GenusName, SpeciesName, Plants.Name, KindsTranslations.Name, Image, SeasonBegin, SeasonEnd, Description " +
            "FROM Plants LEFT JOIN Species ON Plants.IdSpecies = Species.IdSpecies " +
            "LEFT JOIN Genus ON Species.GenusName = Genus.Name " +
            "LEFT JOIN Kinds ON Plants.IdKind = Kinds.IdKind " +
            "LEFT JOIN KindsTranslations ON Kinds.IdKind = KindsTranslations.IdKind " +
            "LEFT JOIN PlantsTranslations ON Plants.IdPlant = PlantsTranslations.IdPlant " +
            "WHERE Plants.IdPlant IN (:idPlant)")
    public abstract Plant getById(int idPlant);

    @Query("SELECT Plants.IdPlant, GenusName, SpeciesName, Plants.Name, KindsTranslations.Name, Image, SeasonBegin, SeasonEnd, Description " +
            "FROM Plants LEFT JOIN Species ON Plants.IdSpecies = Species.IdSpecies " +
            "LEFT JOIN Genus ON Species.GenusName = Genus.Name " +
            "LEFT JOIN Kinds ON Plants.IdKind = Kinds.IdKind " +
            "LEFT JOIN KindsTranslations ON Kinds.IdKind = KindsTranslations.IdKind " +
            "LEFT JOIN PlantsTranslations ON Plants.IdPlant = PlantsTranslations.IdPlant " +
            "WHERE KindsTranslations.Name IN (:kindName)")
    public abstract Maybe<List<Plant>> getAllByKindName(String kindName);



}
