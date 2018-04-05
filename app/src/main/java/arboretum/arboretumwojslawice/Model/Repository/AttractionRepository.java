package arboretum.arboretumwojslawice.Model.Repository;
import android.arch.persistence.room.Database;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.logging.Logger;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.DAO.AttractionDao;
import arboretum.arboretumwojslawice.Model.DAO.AttractionTranslationDao;
import arboretum.arboretumwojslawice.Model.Entity.AttractionEntity;
import arboretum.arboretumwojslawice.Model.Entity.AttractionTranslationEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Attraction;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Komputer on 2018-03-25.
 */

public class AttractionRepository extends BaseRepository {

    @Inject
    AttractionDao attractionDao;
    @Inject
    AttractionTranslationDao attractionTranslationDao;


    @Inject
    public AttractionRepository() {}

    public List<Attraction> getAllAttractions() {
        return attractionDao.getAll();
    }

    public  Attraction getById(int id) {
        return attractionDao.getById(id);
    }

    public  Attraction getByName(String name) {
        return attractionDao.getByName(name);
    }



    public void addAttraction(Attraction attraction) {
        AttractionEntity attractionEntity = new AttractionEntity(attraction.getIdAttraction(), attraction.getImage());
        AttractionTranslationEntity attractionTranslationEntity = new AttractionTranslationEntity(locale.getLanguage(),
                attraction.getIdAttraction(), attraction.getName(), attraction.getDescription());

        /* CHANGE TO TRANSACTION */
        attractionDao.insert(attractionEntity);
        attractionTranslationDao.insert(attractionTranslationEntity);
    }


}


