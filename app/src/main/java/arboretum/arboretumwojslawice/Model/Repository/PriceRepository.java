package arboretum.arboretumwojslawice.Model.Repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import arboretum.arboretumwojslawice.Model.DAO.PriceDao;
import arboretum.arboretumwojslawice.Model.DAO.PriceTranslationDao;
import arboretum.arboretumwojslawice.Model.businessentity.Price;

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


//    /* Constructor without Dagger */
//    public PriceRepository(Application appliaction, AppDatabase db) {
//        this.priceDao = db.getPriceDao();
//        this.priceTranslationDao = db.getPriceTranslationDao();
//        this.languageCode = appliaction.getResources().getConfiguration().locale.getLanguage();
//    }

    public List<Price> getAllPrices(int kind) {
        return priceDao.getAll(languageCode, kind);
    }

    public Price getById(int id, int kind) {
        return priceDao.getById(id, languageCode, kind);
    }

    public List<Price> getByType(String type, int kind) {
        return priceDao.getByType(type, languageCode, kind);
    }
}
