package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.LocationEntity;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-23.
 */

@Dao
abstract class LocationDao implements BaseDao<LocationEntity> {

    @Query("SELECT * FROM Location")
    abstract Maybe<List<LocationEntity>> getAll();

    @Query("SELECT * FROM Location WHERE IdLocation IN (:id)")
    abstract LocationEntity getByLocationId(int id);

    @Query("SELECT * FROM Location WHERE IdPlant IN (:id)")
    abstract Maybe<List<LocationEntity>> getAllByPlantId(int id);

    
}
