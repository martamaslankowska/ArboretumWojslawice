package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.Price;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
public interface PriceDao {

    @Query("SELECT * FROM Prices")
    Maybe<List<Price>> getAll();

    @Query("SELECT * FROM Prices WHERE IdPrice IN (:id)")
    Price getById(int id);

    @Query("SELECT * FROM Prices WHERE Type IN (:type)")
    Maybe<List<Price>> getByType(String type);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(Price... prices);

    @Insert
    void insert(List<Price> prices);

    @Delete
    void delete(Price... prices);

    @Delete
    void delete(List<Price> prices);

    @Update
    void update(Price... prices);

    @Update
    void update(List<Price> prices);


}