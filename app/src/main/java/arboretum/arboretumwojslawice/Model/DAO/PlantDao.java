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
public interface PlantDao {

    @Query("SELECT * FROM PlantEntity")
    Maybe<List<PlantEntity>> getAll();

    @Query("SELECT * FROM PlantEntity WHERE IdPlant IN (:id)")
    PlantEntity getById(int id);

    @Query("SELECT * FROM PlantEntity WHERE Name IN (:name) LIMIT 1")
    PlantEntity getByName(int name);

    @Query("SELECT * FROM PlantEntity WHERE IdKind IN (:kindId)")
    Maybe<List<PlantEntity>> getAllByKindId(int kindId);

    @Query("SELECT IdPlant, Plant.Name,  IdSpecies, Plant.IdKind, Kind.Name, Image, SeasonBegin, SeasonEnd " +
            "FROM PlantEntity INNER JOIN KindEntity ON PlantEntity.IdKind = KindEntity.IdKind WHERE Kind.Name IN (:kindName)")
    Maybe<List<PlantEntity>> getAllByKindName(int kindName);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(PlantEntity... plants);

    @Insert
    void insert(List<PlantEntity> plants);

    @Delete
    void delete(PlantEntity... plants);

    @Delete
    void delete(List<PlantEntity> plants);

    @Update
    void update(PlantEntity... plants);

    @Update
    void update(List<PlantEntity> plants);


}
