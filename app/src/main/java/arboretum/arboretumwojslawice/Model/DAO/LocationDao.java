package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.Location;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-23.
 */

@Dao
public interface LocationDao {

    @Query("SELECT * FROM Location")
    Maybe<List<Location>> getAll();

    @Query("SELECT * FROM Location WHERE IdLocation IN (:id)")
    Location getByLocationId(int id);

    @Query("SELECT * FROM Location WHERE IdPlant IN (:id)")
    Maybe<List<Location>> getAllByPlantId(int id);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(Location... locations);

    @Insert
    void insert(List<Location> locations);

    @Delete
    void delete(Location... locations);

    @Delete
    void delete(List<Location> locations);

    @Update
    void update(Location... locations);

    @Update
    void update(List<Location> locations);

    
}
