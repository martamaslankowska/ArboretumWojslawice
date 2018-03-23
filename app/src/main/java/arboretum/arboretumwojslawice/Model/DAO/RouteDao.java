package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.Route;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-23.
 */


@Dao
public interface RouteDao {

    @Query("SELECT * FROM Routes")
    Maybe<List<Route>> getAll();

    @Query("SELECT * FROM Routes WHERE IdRoute IN (:id)")
    Route getById(int id);

    @Query("SELECT * FROM Routes WHERE Name IN (:name) LIMIT 1")
    Route getByName(int name);



    // INSERT, DELETE and UPDATE

    @Insert
    void insert(Route... routes);

    @Insert
    void insert(List<Route> routes);

    @Delete
    void delete(Route... routes);

    @Delete
    void delete(List<Route> routes);

    @Update
    void update(Route... routes);

    @Update
    void update(List<Route> routes);
}
