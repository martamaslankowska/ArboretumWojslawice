package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.SpeciesEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Price;

/**
 * Created by Komputer on 2018-03-26.
 */

@Dao
public abstract class SpeciesDao extends BaseDao<SpeciesEntity> {

    @Query("SELECT IdSpecies " +
            "FROM Species " +
            "WHERE SpeciesName IN (:speciesName) AND GenusName IN (:genusName)")
    public abstract Integer getSpeciesId(String genusName, String speciesName);


}
