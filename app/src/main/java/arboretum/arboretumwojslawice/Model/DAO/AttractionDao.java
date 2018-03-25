package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.AttractionEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Attraction;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
abstract class AttractionDao implements BaseDao<AttractionEntity> {

    @Query("SELECT * FROM Attractions")
    abstract Maybe<List<Attraction>> getAll();

    @Query("SELECT * FROM Attractions WHERE IdAttraction IN (:id)")
    abstract Attraction getById(int id);

    @Query("SELECT * FROM Attractions WHERE Name IN (:name) LIMIT 1")
    abstract AttractionEntity getByName(String Name);


}