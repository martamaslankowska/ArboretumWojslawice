package arboretum.arboretumwojslawice.Model.Repository;

import android.arch.persistence.room.Database;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Observable;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.DAO.AttractionDao;
import arboretum.arboretumwojslawice.Model.DAO.AttractionTranslationDao;
import arboretum.arboretumwojslawice.Model.Entity.AttractionEntity;
import arboretum.arboretumwojslawice.Model.Entity.AttractionTranslationEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Attraction;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-25.
 */

public class AttractionRepository extends BaseRepository {

    private AttractionDao attractionDao;
    private AttractionTranslationDao attractionTranslationDao;

    @Inject
    public AttractionRepository(AttractionDao attractionDao, AttractionTranslationDao attractionTranslationDao, Locale locale) {
        this.attractionDao = attractionDao;
        this.attractionTranslationDao = attractionTranslationDao;
        this.locale = locale;
    }

    public Maybe<List<Attraction>> getAllAttractions() {
        return attractionDao.getAll();
    }

    public void addAttraction(Attraction attraction) {
        AttractionEntity attractionEntity = new AttractionEntity(attraction.getIdAttraction(), attraction.getImage());
        AttractionTranslationEntity attractionTranslationEntity = new AttractionTranslationEntity(locale.getLanguage(),
                attraction.getIdAttraction(), attraction.getName(), attraction.getDescription());

        attractionDao.insert(attractionEntity);
        attractionTranslationDao.insert(attractionTranslationEntity);
    }

}


