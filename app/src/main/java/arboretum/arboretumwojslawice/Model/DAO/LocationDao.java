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
public interface LocationDao {

    @Query("SELECT * FROM Location")
    Maybe<List<LocationEntity>> getAll();

    @Query("SELECT * FROM Location WHERE IdLocation IN (:id)")
    LocationEntity getByLocationId(int id);

    @Query("SELECT * FROM Location WHERE IdPlant IN (:id)")
    Maybe<List<LocationEntity>> getAllByPlantId(int id);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(LocationEntity... locations);

    @Insert
    void insert(List<LocationEntity> locations);

    @Delete
    void delete(LocationEntity... locations);

    @Delete
    void delete(List<LocationEntity> locations);

    @Update
    void update(LocationEntity... locations);

    @Update
    void update(List<LocationEntity> locations);

    
}
