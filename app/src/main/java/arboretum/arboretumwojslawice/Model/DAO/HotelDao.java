package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.HotelEntity;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
public interface HotelDao {

    @Query("SELECT * FROM HotelEntity")
    Maybe<List<HotelEntity>> getAll();

    @Query("SELECT * FROM HotelEntity WHERE IdHotel IN (:id)")
    HotelEntity getById(int id);

    @Query("SELECT * FROM HotelEntity WHERE Name IN (:name)")
    HotelEntity getByName(String name);

    @Query("SELECT * FROM HotelEntity WHERE Rating >= (:rating)")
    Maybe<List<HotelEntity>> getAllBetterThan(double rating);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(HotelEntity... hotels);

    @Insert
    void insert(List<HotelEntity> hotels);

    @Delete
    void delete(HotelEntity... hotels);

    @Delete
    void delete(List<HotelEntity> hotels);

    @Update
    void update(HotelEntity... hotels);

    @Update
    void update(List<HotelEntity> hotels);


}