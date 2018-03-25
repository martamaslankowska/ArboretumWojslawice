package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.PriceEntity;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
public interface PriceDao {

    @Query("SELECT * FROM PriceEntity")
    Maybe<List<PriceEntity>> getAll();

    @Query("SELECT * FROM PriceEntity WHERE IdPrice IN (:id)")
    PriceEntity getById(int id);

    @Query("SELECT * FROM PriceEntity WHERE Type IN (:type)")
    Maybe<List<PriceEntity>> getByType(String type);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(PriceEntity... prices);

    @Insert
    void insert(List<PriceEntity> prices);

    @Delete
    void delete(PriceEntity... prices);

    @Delete
    void delete(List<PriceEntity> prices);

    @Update
    void update(PriceEntity... prices);

    @Update
    void update(List<PriceEntity> prices);


}