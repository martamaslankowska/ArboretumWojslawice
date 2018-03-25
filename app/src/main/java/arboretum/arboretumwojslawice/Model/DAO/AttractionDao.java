package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.AttractionEntity;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
public interface AttractionDao {

    @Query("SELECT * FROM AttractionEntity")
    Maybe<List<AttractionEntity>> getAll();

    @Query("SELECT * FROM AttractionEntity WHERE IdAttraction IN (:id)")
    AttractionEntity getById(int id);

    @Query("SELECT * FROM AttractionEntity WHERE Name IN (:name) LIMIT 1")
    AttractionEntity getByName(String Name);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(AttractionEntity... attractions);

    @Insert
    void insert(List<AttractionEntity> attractions);

    @Delete
    void delete(AttractionEntity... attractions);

    @Delete
    void delete(List<AttractionEntity> attractions);

    @Update
    void update(AttractionEntity... attractions);

    @Update
    void update(List<AttractionEntity> attractions);


}