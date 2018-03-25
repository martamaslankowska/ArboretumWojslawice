package arboretum.arboretumwojslawice.Model.DAO;
import arboretum.arboretumwojslawice.Model.Entity.PlantEntity;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import io.reactivex.Maybe;
import java.util.List;


/**
 * Created by Komputer on 2018-03-23.
 */


@Dao
abstract class PlantDao implements BaseDao<PlantEntity> {

    @Query("SELECT * FROM PlantEntity")
    abstract Maybe<List<PlantEntity>> getAll();

    @Query("SELECT * FROM PlantEntity WHERE IdPlant IN (:id)")
    abstract PlantEntity getById(int id);

    @Query("SELECT * FROM PlantEntity WHERE Name IN (:name) LIMIT 1")
    abstract PlantEntity getByName(int name);

    @Query("SELECT * FROM PlantEntity WHERE IdKind IN (:kindId)")
    abstract Maybe<List<PlantEntity>> getAllByKindId(int kindId);

    @Query("SELECT IdPlant, Plant.Name,  IdSpecies, Plant.IdKind, Kind.Name, Image, SeasonBegin, SeasonEnd " +
            "FROM PlantEntity INNER JOIN KindEntity ON PlantEntity.IdKind = KindEntity.IdKind WHERE Kind.Name IN (:kindName)")
    abstract Maybe<List<PlantEntity>> getAllByKindName(int kindName);



}
