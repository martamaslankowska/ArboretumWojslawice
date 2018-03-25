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
abstract class FavouritePlantDao implements BaseDao<FavouritePlantEntity> {
    
    @Query("SELECT * FROM FavouritePlantEntity")
    abstract Maybe<List<FavouritePlantEntity>> getAll();

    @Query("SELECT Plant.IdPlant, Name, IdSpecies, IdKind, Image, SeasonBegin, SeasonEnd " +
            "FROM PlantEntity INNER JOIN FavouritePlantEntity ON PlantEntity.IdPlant = FavouritePlantEntity.IdPlant WHERE FavouritePlantEntity.IdPlant IN (:id)")
    abstract Maybe<List<PlantEntity>> getById(int id);

}
