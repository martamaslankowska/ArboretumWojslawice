package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Komputer on 2018-03-25.
 */

@Dao
abstract class BaseDao<T> {  // extends Entity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(T... objects);

    @Insert
    public abstract void insert(List<T> objects);

    @Delete
    public abstract void delete(T... objects);

    @Delete
    public abstract void delete(List<T> objects);

    @Update
    public abstract void update(T... objects);

    @Update
    public abstract void update(List<T> objects);

}
