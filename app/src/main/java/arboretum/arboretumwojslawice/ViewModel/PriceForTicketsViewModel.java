package arboretum.arboretumwojslawice.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.di.AppController;
import arboretum.arboretumwojslawice.Model.AppDatabase;
import arboretum.arboretumwojslawice.Model.Repository.PriceRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Price;

public class PriceForTicketsViewModel {

    @Inject
    protected AppDatabase db;
    @Inject
    protected PriceRepository repository;

    @Inject
    public PriceForTicketsViewModel(AppController appController) {
        //db = AppDatabase.getAppDatabase(appController.getApplicationContext());
        //repository = new PriceRepository(appController, db);
    }

    public List<Price> getAllPrices() {
        return repository.getAllPrices();
    }

    public Price getPrice(int idPrice) {
        return repository.getById(idPrice);
    }

    public List<Price> getPriceForMichal()
    {
        List<Price> list = new ArrayList<>();

        list.add(new Price(1,10.0,"Ulgowy", "Bilet dla dzieci", 0));
        list.add(new Price(2,25.0,"Normalny", "Bilet dla dorosłych", 0));

        return list;
    }


}
