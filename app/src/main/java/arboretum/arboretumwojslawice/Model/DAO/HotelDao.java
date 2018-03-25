package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.HotelEntity;
import arboretum.arboretumwojslawice.Model.Entity.HotelEntity;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
abstract class HotelDao implements BaseDao<HotelEntity> {

    @Query("SELECT * FROM HotelEntity")
    abstract Maybe<List<HotelEntity>> getAll();

    @Query("SELECT * FROM HotelEntity WHERE IdHotel IN (:id)")
    abstract HotelEntity getById(int id);

    @Query("SELECT * FROM HotelEntity WHERE Name IN (:name)")
    abstract HotelEntity getByName(String name);

    @Query("SELECT * FROM HotelEntity WHERE Rating >= (:rating)")
    abstract Maybe<List<HotelEntity>> getAllBetterThan(double rating);



}