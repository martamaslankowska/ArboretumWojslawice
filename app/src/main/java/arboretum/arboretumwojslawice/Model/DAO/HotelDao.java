package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.Hotel;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
public interface HotelDao {

    @Query("SELECT * FROM Hotels")
    Maybe<List<Hotel>> getAll();

    @Query("SELECT * FROM Hotels WHERE IdHotel IN (:id)")
    Hotel getById(int id);

    @Query("SELECT * FROM Hotels WHERE Name IN (:name)")
    Hotel getByName(String name);

    @Query("SELECT * FROM Hotels WHERE Rating >= (:rating)")
    Maybe<List<Hotel>> getAllBetterThan(double rating);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(Hotel... hotels);

    @Insert
    void insert(List<Hotel> hotels);

    @Delete
    void delete(Hotel... hotels);

    @Delete
    void delete(List<Hotel> hotels);

    @Update
    void update(Hotel... hotels);

    @Update
    void update(List<Hotel> hotels);


}