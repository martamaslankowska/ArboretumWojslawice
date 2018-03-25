package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.PriceEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Price;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
abstract class PriceDao implements BaseDao<PriceEntity> {

    @Query("SELECT Prices.IdPrice, Amount, Type " +
            "FROM Prices LEFT JOIN PricesTranslations ON Price.IdPrice = PricesTranslations.IdPrice")
    abstract Maybe<List<Price>> getAll();

    @Query("SELECT Prices.IdPrice, Amount, Type " +
            "FROM Prices LEFT JOIN PricesTranslations ON Price.IdPrice = PricesTranslations.IdPrice" +
            " WHERE IdPrice IN (:id)")
    abstract Price getById(int id);

    @Query("SELECT Prices.IdPrice, Amount, Type " +
            "FROM Prices LEFT JOIN PricesTranslations ON Price.IdPrice = PricesTranslations.IdPrice" +
            " WHERE Type IN (:type)")
    abstract Maybe<List<Price>> getByType(String type);


}