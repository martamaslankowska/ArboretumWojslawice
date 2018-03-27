package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.AttractionEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Attraction;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
public abstract class AttractionDao extends BaseDao<AttractionEntity> {

    @Query("SELECT Attractions.IdAttraction, Name, Description, Image " +
            "FROM Attractions INNER JOIN AttractionsTranslations ON Attractions.IdAttraction = AttractionsTranslations.IdAttraction")
    abstract Cursor getAll();

    @Query("SELECT Attractions.IdAttraction, Name, Description, Image " +
            "FROM Attractions INNER JOIN AttractionsTranslations ON Attractions.IdAttraction = AttractionsTranslations.IdAttraction " +
            "WHERE Attractions.IdAttraction IN (:id)")
    abstract Cursor getById(int id);

    @Query("SELECT Attractions.IdAttraction, Name, Description, Image " +
            "FROM Attractions INNER JOIN AttractionsTranslations ON Attractions.IdAttraction = AttractionsTranslations.IdAttraction " +
            "WHERE Name IN (:name)")
    abstract Cursor getByName(int name);

}