package arboretum.arboretumwojslawice.Model.Repository;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.DAO.PriceDao;
import arboretum.arboretumwojslawice.Model.DAO.PriceTranslationDao;
import arboretum.arboretumwojslawice.Model.businessentity.Price;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Komputer on 2018-03-25.
 */

public class PriceRepository extends BaseRepository {

    private PriceDao priceDao;
    private PriceTranslationDao priceTranslationDao;

    @Inject
    public PriceRepository(PriceDao PriceDao, PriceTranslationDao PriceTranslationDao, Locale locale) {
        this.priceDao = PriceDao;
        this.priceTranslationDao = PriceTranslationDao;
        this.locale = locale;
    }

    public Maybe<List<Price>> getAllPrices() {
        return priceDao.getAll();
    }

    public Single<Price> getById(int id) {
        return priceDao.getById(id);
    }

    public Maybe<List<Price>> getByType(String type) {
        return priceDao.getByType(type);
    }
}
