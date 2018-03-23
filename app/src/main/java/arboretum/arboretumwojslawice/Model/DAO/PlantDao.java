package arboretum.arboretumwojslawice.Model.DAO;
import arboretum.arboretumwojslawice.Model.Entity.Plant;

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

    @Query("SELECT * FROM Plants")
    Maybe<List<Plant>> getAll();

    @Query("SELECT * FROM Plants WHERE IdPlant IN (:id)")
    Plant getById(int id);

    @Query("SELECT * FROM Plants WHERE Name IN (:name) LIMIT 1")
    Plant getByName(int name);

    @Query("SELECT * FROM Plants WHERE IdKind IN (:kindId)")
    Maybe<List<Plant>> getAllByKindId(int kindId);

    @Query("SELECT IdPlant, Plant.Name,  IdSpecies, Plant.IdKind, Kind.Name, Image, SeasonBegin, SeasonEnd " +
            "FROM Plants INNER JOIN Kinds ON Plants.IdKind = Kinds.IdKind WHERE Kind.Name IN (:kindName)")
    Maybe<List<Plant>> getAllByKindName(int kindName);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(Plant... plants);

    @Insert
    void insert(List<Plant> plants);

    @Delete
    void delete(Plant... plants);

    @Delete
    void delete(List<Plant> plants);

    @Update
    void update(Plant... plants);

    @Update
    void update(List<Plant> plants);


}
