package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.PriceRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Price;

public class PriceForTicketsViewModel {

    @Inject
    protected PriceRepository repository;
    private final int KIND = 0;

    @Inject
    public PriceForTicketsViewModel() { }


    public List<Price> getAllPrices() {
        return repository.getAllPrices(KIND);
    }

    public Price getPrice(int idPrice) {
        return repository.getById(idPrice, KIND);
    }
}
