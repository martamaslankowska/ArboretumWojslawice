package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import arboretum.arboretumwojslawice.Model.Entity.PriceTranslationEntity;

/**
 * Created by Komputer on 2018-03-26.
 */

@Dao
public abstract class PriceTranslationDao  extends BaseDao<PriceTranslationEntity> {

    /* probably temporary */
    @Query("DELETE FROM PricesTranslations")
    public abstract void deleteAll();

}
