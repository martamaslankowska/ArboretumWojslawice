package arboretum.arboretumwojslawice.Model.DAO;
import arboretum.arboretumwojslawice.Model.Entity.KindEntity;
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
abstract class KindDao implements BaseDao<KindEntity> {

    @Query("SELECT * FROM KindEntity")
    abstract Maybe<List<KindEntity>> getAll();

    @Query("SELECT * FROM KindEntity WHERE IdKind IN (:id)")
    abstract KindEntity getById(int id);

    @Query("SELECT * FROM KindEntity WHERE Name IN (:name) LIMIT 1")
    abstract KindEntity getByName(int name);


    
}
