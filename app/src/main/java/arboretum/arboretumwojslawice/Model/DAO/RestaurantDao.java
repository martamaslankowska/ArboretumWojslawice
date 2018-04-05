package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.RestaurantEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Hotel;
import arboretum.arboretumwojslawice.Model.businessentity.Restaurant;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
public abstract class RestaurantDao extends BaseDao<RestaurantEntity> {

    @Query("SELECT * FROM Restaurants")
    public abstract List<Restaurant> getAll();

    @Query("SELECT * FROM Restaurants WHERE IdRestaurant IN (:id)")
    public abstract  Restaurant getById(int id);

    @Query("SELECT * FROM Restaurants WHERE Name IN (:name) LIMIT 1")
    public abstract  Restaurant getByName(String name);

    @Query("SELECT * FROM Restaurants WHERE Rating >= (:rating)")
    public abstract List<Restaurant> getAllBetterThan(double rating);


}