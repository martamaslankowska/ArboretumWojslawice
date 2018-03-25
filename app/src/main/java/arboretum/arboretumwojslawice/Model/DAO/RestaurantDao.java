package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.RestaurantEntity;
import arboretum.arboretumwojslawice.Model.Entity.RestaurantEntity;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
abstract class RestaurantDao implements BaseDao<RestaurantEntity> {

    @Query("SELECT * FROM RestaurantEntity")
    abstract Maybe<List<RestaurantEntity>> getAll();

    @Query("SELECT * FROM RestaurantEntity WHERE IdRestaurant IN (:id)")
    abstract RestaurantEntity getById(int id);

    @Query("SELECT * FROM RestaurantEntity WHERE Name IN (:name)")
    abstract RestaurantEntity getByName(String name);

    @Query("SELECT * FROM RestaurantEntity WHERE Rating >= (:rating)")
    abstract Maybe<List<RestaurantEntity>> getAllBetterThan(double rating);


}