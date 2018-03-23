package arboretum.arboretumwojslawice.Model.DAO;
import arboretum.arboretumwojslawice.Model.Entity.Kind;

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

    @Query("SELECT * FROM Kinds")
    Maybe<List<Kind>> getAll();

    @Query("SELECT * FROM Kinds WHERE IdKind IN (:id)")
    Kind getById(int id);

    @Query("SELECT * FROM Plants WHERE Name IN (:name) LIMIT 1")
    Kind getByName(int name);
    

    // INSERT, DELETE and UPDATE

    @Insert
    void insert(Kind... kinds);

    @Insert
    void insert(List<Kind> kinds);

    @Delete
    void delete(Kind... kinds);

    @Delete
    void delete(List<Kind> kinds);

    @Update
    void update(Kind... kinds);

    @Update
    void update(List<Kind> kinds);
    
}
