package arboretum.arboretumwojslawice.Model.DAO;
import arboretum.arboretumwojslawice.Model.Entity.KindEntity;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-23.
 */


@Dao
public interface KindDao {

    @Query("SELECT * FROM KindEntity")
    Maybe<List<KindEntity>> getAll();

    @Query("SELECT * FROM KindEntity WHERE IdKind IN (:id)")
    KindEntity getById(int id);

    @Query("SELECT * FROM KindEntity WHERE Name IN (:name) LIMIT 1")
    KindEntity getByName(int name);


    // INSERT, DELETE and UPDATE

    @Insert
    void insert(KindEntity... kinds);

    @Insert
    void insert(List<KindEntity> kinds);

    @Delete
    void delete(KindEntity... kinds);

    @Delete
    void delete(List<KindEntity> kinds);

    @Update
    void update(KindEntity... kinds);

    @Update
    void update(List<KindEntity> kinds);
    
}
