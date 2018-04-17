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
import arboretum.arboretumwojslawice.Model.Repository.PriceRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Price;

public class PriceViewModel {

    AppDatabase db;
    PriceRepository repository;

    public PriceViewModel(Application application) {
        db = AppDatabase.getAppDatabase(application.getApplicationContext());
        repository = new PriceRepository(application, db);
    }

    public List<Price> getAllPrices() {
        return repository.getAllPrices();
    }

    public Price getPrice(int idPrice) {
        return repository.getById(idPrice);
    }


}
