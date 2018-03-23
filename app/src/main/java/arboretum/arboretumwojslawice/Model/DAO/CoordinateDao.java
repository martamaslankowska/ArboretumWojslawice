package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.Coordinate;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-23.
 */

@Dao
public interface CoordinateDao {

    @Query("SELECT * FROM Coordinates")
    Maybe<List<Coordinate>> getAll();

    @Query("SELECT * FROM Coordinates WHERE IdCoordinate IN (:id)")
    Coordinate getByCoordinateId(int id);

    @Query("SELECT * FROM Coordinates WHERE IdPlant IN (:id)")
    Maybe<List<Coordinate>> getAllByPlantId(int id);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(Coordinate... coordinates);

    @Insert
    void insert(List<Coordinate> coordinates);

    @Delete
    void delete(Coordinate... coordinates);

    @Delete
    void delete(List<Coordinate> coordinates);

    @Update
    void update(Coordinate... coordinates);

    @Update
    void update(List<Coordinate> coordinates);

    
}
