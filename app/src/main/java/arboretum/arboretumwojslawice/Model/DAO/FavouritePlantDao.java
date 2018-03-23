package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.FavouritePlant;
import arboretum.arboretumwojslawice.Model.Entity.Plant;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-23.
 */


@Dao
public interface FavouritePlantDao {

    @Query("SELECT * FROM FavouritePlants")
    Maybe<List<FavouritePlant>> getAll();

    @Query("SELECT Plant.IdPlant, Name, IdSpecies, IdKind, Image, SeasonBegin, SeasonEnd " +
            "FROM Plants INNER JOIN FavouritePlants ON Plants.IdPlant = FavouritePlants.IdPlant WHERE FavouritePlants.IdPlant IN (:id)")
    Maybe<List<Plant>> getById(int id);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(FavouritePlant... favouritePlants);

    @Insert
    void insert(List<FavouritePlant> favouritePlants);

    @Delete
    void delete(FavouritePlant... favouritePlants);

    @Delete
    void delete(List<FavouritePlant> favouritePlants);

    @Update
    void update(FavouritePlant... favouritePlants);

    @Update
    void update(List<FavouritePlant> favouritePlants);
    
}
