package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.FavouritePlantEntity;
import arboretum.arboretumwojslawice.Model.Entity.PlantEntity;
import arboretum.arboretumwojslawice.Model.businessentity.FavouritePlant;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-23.
 */


@Dao
public abstract class FavouritePlantDao extends BaseDao<FavouritePlantEntity> {
    
//    @Query("SELECT * FROM FavouritePlants")
//    abstract Maybe<List<FavouritePlant>> getAll();
//
//    @Query("SELECT Plant.IdPlant, Name, IdSpecies, IdKind, Image, SeasonBegin, SeasonEnd " +
//            "FROM Plans INNER JOIN FavouritePlants ON Plants.IdPlant = FavouritePlants.IdPlant WHERE FavouritePlants.IdPlant IN (:id)")
//    abstract Maybe<List<FavouritePlant>> getById(int id);

}
