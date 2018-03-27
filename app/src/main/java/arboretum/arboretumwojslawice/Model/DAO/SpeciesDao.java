package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;

import arboretum.arboretumwojslawice.Model.Entity.SpeciesEntity;

/**
 * Created by Komputer on 2018-03-26.
 */

@Dao
public abstract class SpeciesDao implements BaseDao<SpeciesEntity> {}
