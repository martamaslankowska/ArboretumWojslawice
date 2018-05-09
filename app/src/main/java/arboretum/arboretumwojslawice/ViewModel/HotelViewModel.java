package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.HotelRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Hotel;

public class HotelViewModel {

    @Inject
    HotelRepository hotelRepo;

    @Inject
    HotelViewModel() {}

    public List<Hotel> getAll() {
        return hotelRepo.getAllHotels();
    }
}
