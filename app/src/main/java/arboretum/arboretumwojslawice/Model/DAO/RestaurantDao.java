package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.Restaurant;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
public interface RestaurantDao {

    @Query("SELECT * FROM Restaurants")
    Maybe<List<Restaurant>> getAll();

    @Query("SELECT * FROM Restaurants WHERE IdRestaurant IN (:id)")
    Restaurant getById(int id);

    @Query("SELECT * FROM Restaurants WHERE Name IN (:name)")
    Restaurant getByName(String name);

    @Query("SELECT * FROM Restaurants WHERE Rating >= (:rating)")
    Maybe<List<Restaurant>> getAllBetterThan(double rating);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(Restaurant... restaurants);

    @Insert
    void insert(List<Restaurant> restaurants);

    @Delete
    void delete(Restaurant... restaurants);

    @Delete
    void delete(List<Restaurant> restaurants);

    @Update
    void update(Restaurant... restaurants);

    @Update
    void update(List<Restaurant> restaurants);


}