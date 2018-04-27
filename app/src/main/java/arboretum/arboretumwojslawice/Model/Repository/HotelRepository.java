package arboretum.arboretumwojslawice.Model.Repository;

import android.app.Application;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.AppDatabase;
import arboretum.arboretumwojslawice.Model.DAO.HotelDao;
import arboretum.arboretumwojslawice.Model.businessentity.Hotel;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Komputer on 2018-03-25.
 */

public class HotelRepository extends BaseRepository {

    @Inject
    HotelDao hotelDao;

    @Inject
    public HotelRepository() {}

    /* Constructor without Dagger */
    public HotelRepository(AppDatabase db) {
        this.hotelDao = db.getHotelDao();
    }

    public List<Hotel> getAllHotels() {
        return hotelDao.getAll();
    }

    public  Hotel getById(int id) {
        return hotelDao.getById(id);
    }

    public  Hotel getByName(String name) {
        return hotelDao.getByName(name);
    }

    public List<Hotel> getAllBetterThan(double rating) {
        return hotelDao.getAllBetterThan(rating);
    }
    
}
