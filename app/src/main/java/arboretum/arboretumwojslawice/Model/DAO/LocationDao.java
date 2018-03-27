package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;

import arboretum.arboretumwojslawice.Model.Entity.LocationEntity;

/**
 * Created by Komputer on 2018-03-23.
 */

@Dao
public abstract class LocationDao extends BaseDao<LocationEntity> {}
