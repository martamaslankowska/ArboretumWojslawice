package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.PriceEntity;
import arboretum.arboretumwojslawice.Model.Entity.PriceEntity;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
abstract class PriceDao implements BaseDao<PriceEntity> {

    @Query("SELECT * FROM PriceEntity")
    abstract Maybe<List<PriceEntity>> getAll();

    @Query("SELECT * FROM PriceEntity WHERE IdPrice IN (:id)")
    abstract PriceEntity getById(int id);

    @Query("SELECT * FROM PriceEntity WHERE Type IN (:type)")
    abstract Maybe<List<PriceEntity>> getByType(String type);


}