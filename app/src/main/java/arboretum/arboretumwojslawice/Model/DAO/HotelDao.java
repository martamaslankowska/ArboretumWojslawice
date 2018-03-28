package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.HotelEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Hotel;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
public abstract class HotelDao extends BaseDao<HotelEntity> {

    @Query("SELECT * FROM Hotels")
    public abstract Maybe<List<Hotel>> getAll();

    @Query("SELECT * FROM Hotels WHERE IdHotel IN (:id)")
    public abstract Hotel getById(int id);

    @Query("SELECT * FROM Hotels WHERE Name IN (:name)")
    public abstract Hotel getByName(String name);

    @Query("SELECT * FROM Hotels WHERE Rating >= (:rating)")
    public abstract Maybe<List<Hotel>> getAllBetterThan(double rating);

}