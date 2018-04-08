package arboretum.arboretumwojslawice.ViewModel;

import android.app.Application;
import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.room.Room;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import arboretum.arboretumwojslawice.Model.AppDatabase;
import arboretum.arboretumwojslawice.Model.DAO.PriceDao;
import arboretum.arboretumwojslawice.Model.DatabaseHelper;
import arboretum.arboretumwojslawice.Model.businessentity.Price;

public class PriceViewModel {

    /* DELETE WHEN DAGGER STARTS WORKING... */
    Application app;
    AppDatabase db;
    PriceDao priceDao;

    List<Price> mPrices;

    public PriceViewModel(Application application) {
        app = application;
        db = AppDatabase.getAppDatabase(app.getApplicationContext());
        priceDao = db.getPriceDao();
    }

    public List<Price> getAllPrices() {
        String translationCode = app.getResources().getConfiguration().locale.getLanguage();
        mPrices = priceDao.getAll(translationCode);
        return mPrices;
    }


}
