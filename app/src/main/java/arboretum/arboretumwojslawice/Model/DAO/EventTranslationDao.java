package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import arboretum.arboretumwojslawice.Model.Entity.EventTranslationEntity;

/**
 * Created by Komputer on 2018-03-25.
 */

@Dao
public abstract class EventTranslationDao extends BaseDao<EventTranslationEntity> {

    /* probably temporary */
    @Query("DELETE FROM EventsTranslations")
    public abstract void deleteAll();

}
