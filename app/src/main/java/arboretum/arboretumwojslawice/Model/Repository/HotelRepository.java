package arboretum.arboretumwojslawice.Model.Repository;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.DAO.HotelDao;
import arboretum.arboretumwojslawice.Model.businessentity.Hotel;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Komputer on 2018-03-25.
 */

public class HotelRepository extends BaseRepository {

    private HotelDao hotelDao;

    @Inject
    public HotelRepository(HotelDao HotelDao, Locale locale) {
        this.hotelDao = HotelDao;
        this.locale = locale;
    }

    public Maybe<List<Hotel>> getAllHotels() {
        return hotelDao.getAll();
    }

    public Single<Hotel> getById(int id) {
        return hotelDao.getById(id);
    }

    public Single<Hotel> getByName(String name) {
        return hotelDao.getByName(name);
    }

    public Maybe<List<Hotel>> getAllBetterThan(double rating) {
        return hotelDao.getAllBetterThan(rating);
    }
    
}
