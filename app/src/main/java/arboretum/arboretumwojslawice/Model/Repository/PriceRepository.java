package arboretum.arboretumwojslawice.Model.Repository;

import android.app.Application;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.AppDatabase;
import arboretum.arboretumwojslawice.Model.DAO.PriceDao;
import arboretum.arboretumwojslawice.Model.DAO.PriceTranslationDao;
import arboretum.arboretumwojslawice.Model.businessentity.Price;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Komputer on 2018-03-25.
 */

public class PriceRepository extends BaseRepository {

    @Inject
    PriceDao priceDao;
    @Inject
    PriceTranslationDao priceTranslationDao;

    @Inject
    public PriceRepository() {}


    /* Constructor without Dagger */
    public PriceRepository(Application appliaction, AppDatabase db) {
        this.priceDao = db.getPriceDao();
        this.priceTranslationDao = db.getPriceTranslationDao();
        this.languageCode = appliaction.getResources().getConfiguration().locale.getLanguage();
    }

    public List<Price> getAllPrices() {
        return priceDao.getAll(languageCode);
    }

    public Price getById(int id) {
        return priceDao.getById(id, languageCode);
    }

    public List<Price> getByType(String type) {
        return priceDao.getByType(type, languageCode);
    }
}
