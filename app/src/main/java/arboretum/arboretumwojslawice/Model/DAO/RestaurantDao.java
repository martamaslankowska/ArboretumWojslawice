package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.RestaurantEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Restaurant;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
abstract class RestaurantDao implements BaseDao<RestaurantEntity> {

    @Query("SELECT * FROM Restaurants")
    abstract Maybe<List<Restaurant>> getAll();

    @Query("SELECT * FROM Restaurants WHERE IdRestaurant IN (:id)")
    abstract Restaurant getById(int id);

    @Query("SELECT * FROM Restaurants WHERE Name IN (:name) LIMIT 1")
    abstract Restaurant getByName(String name);

    @Query("SELECT * FROM Restaurants WHERE Rating >= (:rating)")
    abstract Maybe<List<Restaurant>> getAllBetterThan(double rating);


}