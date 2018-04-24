package arboretum.arboretumwojslawice.ViewModel;

import android.app.Application;

import java.util.List;

import arboretum.arboretumwojslawice.Model.AppDatabase;
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
