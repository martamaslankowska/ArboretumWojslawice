package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import arboretum.arboretumwojslawice.Model.Entity.AttractionTranslationEntity;

/**
 * Created by Komputer on 2018-03-25.
 */

@Dao
public abstract class AttractionTranslationDao extends BaseDao<AttractionTranslationEntity> {

    /* probably temporary */
    @Query("DELETE FROM AttractionsTranslations")
    public abstract void deleteAll();

}
