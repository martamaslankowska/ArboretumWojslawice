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

    public Single<Attraction> getById(int id) {
        return attractionDao.getById(id);
    }

    public Single<Attraction> getByName(String name) {
        return attractionDao.getByName(name);
    }


//    public void test(int id) {
//        final Wrapper<Attraction> resultWrapper = new Wrapper<>();
//        attractionDao.getById(id)
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(data -> { resultWrapper.setContent(data); },
//                        error-> { Log.e("REPO", "An error during retrieving data from database occured."); });
//
//    }
//
//    public void addAttraction(Attraction attraction) {
//        AttractionEntity attractionEntity = new AttractionEntity(attraction.getIdAttraction(), attraction.getImage());
//        AttractionTranslationEntity attractionTranslationEntity = new AttractionTranslationEntity(locale.getLanguage(),
//                attraction.getIdAttraction(), attraction.getName(), attraction.getDescription());
//
//        attractionDao.insert(attractionEntity);
//        attractionTranslationDao.insert(attractionTranslationEntity);
//    }
//
//    private static class Wrapper<T> {
//        private T content;
//
//        public T getContent() {
//            return content;
//        }
//
//        public void setContent(T content) {
//            this.content = content;
//        }
//    }

}


