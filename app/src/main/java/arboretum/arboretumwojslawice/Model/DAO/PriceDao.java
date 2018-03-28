package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.PriceEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Price;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
public abstract class PriceDao extends BaseDao<PriceEntity> {

    @Query("SELECT Prices.IdPrice, Amount, Type " +
            "FROM Prices LEFT JOIN PricesTranslations ON Prices.IdPrice = PricesTranslations.IdPrice")
    public abstract Maybe<List<Price>> getAll();

    @Query("SELECT Prices.IdPrice, Amount, Type " +
            "FROM Prices LEFT JOIN PricesTranslations ON Prices.IdPrice = PricesTranslations.IdPrice" +
            " WHERE Prices.IdPrice IN (:id)")
    public abstract Price getById(int id);

    @Query("SELECT Prices.IdPrice, Amount, Type " +
            "FROM Prices LEFT JOIN PricesTranslations ON Prices.IdPrice = PricesTranslations.IdPrice" +
            " WHERE Type IN (:type)")
    public abstract Maybe<List<Price>> getByType(String type);


}