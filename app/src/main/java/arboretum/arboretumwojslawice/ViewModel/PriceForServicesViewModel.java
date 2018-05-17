package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.PriceRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Price;

public class PriceForServicesViewModel {

    @Inject
    protected PriceRepository repository;
    private final int KIND = 1;

    @Inject
    public PriceForServicesViewModel() { }

    public List<Price> getAllPrices() {
        return repository.getAllPrices(KIND);
    }

    public Price getPrice(int idPrice) {
        return repository.getById(idPrice, KIND);
    }
}
