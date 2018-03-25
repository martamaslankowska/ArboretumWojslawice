package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Komputer on 2018-03-25.
 */

public interface BaseDao<T> {

    @Insert
    void insert(T... objects);

    @Insert
    void insert(List<T> objects);

    @Delete
    void delete(T... objects);

    @Delete
    void delete(List<T> objects);

    @Update
    void update(T... objects);

    @Update
    void update(List<T> objects);

}
