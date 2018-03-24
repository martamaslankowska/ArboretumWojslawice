package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.Attraction;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
public interface AttractionDao {

    @Query("SELECT * FROM Attractions")
    Maybe<List<Attraction>> getAll();

    @Query("SELECT * FROM Attractions WHERE IdAttraction IN (:id)")
    Attraction getById(int id);

    @Query("SELECT * FROM Attractions WHERE Name IN (:name) LIMIT 1")
    Attraction getByName(String Name);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(Attraction... attractions);

    @Insert
    void insert(List<Attraction> attractions);

    @Delete
    void delete(Attraction... attractions);

    @Delete
    void delete(List<Attraction> attractions);

    @Update
    void update(Attraction... attractions);

    @Update
    void update(List<Attraction> attractions);


}