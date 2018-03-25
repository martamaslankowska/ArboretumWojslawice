package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.FavouritePlantEntity;
import arboretum.arboretumwojslawice.Model.Entity.PlantEntity;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-23.
 */


@Dao
public interface FavouritePlantDao {

    @Query("SELECT * FROM FavouritePlantEntity")
    Maybe<List<FavouritePlantEntity>> getAll();

    @Query("SELECT Plant.IdPlant, Name, IdSpecies, IdKind, Image, SeasonBegin, SeasonEnd " +
            "FROM PlantEntity INNER JOIN FavouritePlantEntity ON PlantEntity.IdPlant = FavouritePlantEntity.IdPlant WHERE FavouritePlantEntity.IdPlant IN (:id)")
    Maybe<List<PlantEntity>> getById(int id);


    
}
