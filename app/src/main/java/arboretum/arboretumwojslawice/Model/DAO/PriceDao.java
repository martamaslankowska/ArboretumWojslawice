package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.PriceEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Price;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
public abstract class PriceDao extends BaseDao<PriceEntity> {

    @Query("SELECT Prices.IdPrice, Amount, Type, Description, Kind " +
            "FROM Prices LEFT JOIN PricesTranslations ON Prices.IdPrice = PricesTranslations.IdPrice " +
            "WHERE TranslationCode IN (:translationCode)")
    public abstract List<Price> getAll(String translationCode);

    @Query("SELECT Prices.IdPrice, Amount, Type, Description, Kind " +
            "FROM Prices LEFT JOIN PricesTranslations ON Prices.IdPrice = PricesTranslations.IdPrice" +
            " WHERE Prices.IdPrice IN (:id) AND TranslationCode IN (:translationCode)")
    public abstract  Price getById(int id, String translationCode);

    @Query("SELECT Prices.IdPrice, Amount, Type, Description, Kind " +
            "FROM Prices LEFT JOIN PricesTranslations ON Prices.IdPrice = PricesTranslations.IdPrice" +
            " WHERE Type IN (:type) AND TranslationCode IN (:translationCode)")
    public abstract List<Price> getByType(String type, String translationCode);


    /* probably temporary */
    @Query("DELETE FROM Prices")
    public abstract void deleteAll();

}