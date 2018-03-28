package arboretum.arboretumwojslawice.Model.Repository;

import android.arch.persistence.room.Database;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.DAO.AttractionDao;
import arboretum.arboretumwojslawice.Model.DAO.AttractionTranslationDao;
import arboretum.arboretumwojslawice.Model.businessentity.Attraction;
import io.reactivex.Maybe;

/**
 * Created by Komputer on 2018-03-25.
 */

public class AttractionRepository {

    private AttractionDao attractionDao;
    private AttractionTranslationDao attractionTranslationDao;

    @Inject
    public AttractionRepository(AttractionDao attractionDao, AttractionTranslationDao attractionTranslationDao) {
        this.attractionDao = attractionDao;
        this.attractionTranslationDao = attractionTranslationDao;
    }

    private List<Attraction> getAllAttractions() {
        List<Attraction> attractions = new ArrayList<>();
        return attractions;
    }

}


