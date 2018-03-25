package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.RestaurantEntity;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
public interface RestaurantDao {

    @Query("SELECT * FROM RestaurantEntity")
    Maybe<List<RestaurantEntity>> getAll();

    @Query("SELECT * FROM RestaurantEntity WHERE IdRestaurant IN (:id)")
    RestaurantEntity getById(int id);

    @Query("SELECT * FROM RestaurantEntity WHERE Name IN (:name)")
    RestaurantEntity getByName(String name);

    @Query("SELECT * FROM RestaurantEntity WHERE Rating >= (:rating)")
    Maybe<List<RestaurantEntity>> getAllBetterThan(double rating);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(RestaurantEntity... restaurants);

    @Insert
    void insert(List<RestaurantEntity> restaurants);

    @Delete
    void delete(RestaurantEntity... restaurants);

    @Delete
    void delete(List<RestaurantEntity> restaurants);

    @Update
    void update(RestaurantEntity... restaurants);

    @Update
    void update(List<RestaurantEntity> restaurants);


}