package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import arboretum.arboretumwojslawice.Model.businessentity.Hotel;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
abstract class HotelDao implements BaseDao<Hotel> {

    @Query("SELECT * FROM Hotels")
    abstract Maybe<List<Hotel>> getAll();

    @Query("SELECT * FROM Hotels WHERE IdHotel IN (:id)")
    abstract Hotel getById(int id);

    @Query("SELECT * FROM Hotels WHERE Name IN (:name)")
    abstract Hotel getByName(String name);

    @Query("SELECT * FROM Hotels WHERE Rating >= (:rating)")
    abstract Maybe<List<Hotel>> getAllBetterThan(double rating);

}