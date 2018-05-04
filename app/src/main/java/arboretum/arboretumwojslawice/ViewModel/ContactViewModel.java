package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.HotelRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Hotel;

public class ContactViewModel {

    @Inject
    protected HotelRepository hotelRepo;

    @Inject
    ContactViewModel() { }

    public List<Hotel> getAllHotels() {
        return hotelRepo.getAllHotels();
    }
}
