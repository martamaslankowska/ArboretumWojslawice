package arboretum.arboretumwojslawice.Model.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.AttractionEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Attraction;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Komputer on 2018-03-24.
 */

@Dao
public abstract class AttractionDao extends BaseDao<AttractionEntity> {

    @Query("SELECT Attractions.IdAttraction, Name, Description, Image, Distance " +
            "FROM Attractions INNER JOIN AttractionsTranslations ON Attractions.IdAttraction = AttractionsTranslations.IdAttraction " +
            "WHERE TranslationCode IN (:translationCode)")
    public abstract List<Attraction> getAll(String translationCode);

    @Query("SELECT Attractions.IdAttraction, Name, Description, Image, Distance " +
            "FROM Attractions INNER JOIN AttractionsTranslations ON Attractions.IdAttraction = AttractionsTranslations.IdAttraction " +
            "WHERE Attractions.IdAttraction IN (:id) AND TranslationCode IN (:translationCode)")
    public abstract  Attraction getById(int id, String translationCode);

    @Query("SELECT Attractions.IdAttraction, Name, Description, Image, Distance " +
            "FROM Attractions INNER JOIN AttractionsTranslations ON Attractions.IdAttraction = AttractionsTranslations.IdAttraction " +
            "WHERE Name IN (:name) AND TranslationCode IN (:translationCode)")
    public abstract  Attraction getByName(String name, String translationCode);


    /* probably temporary */
    @Query("DELETE FROM Attractions")
    public abstract void deleteAll();

}