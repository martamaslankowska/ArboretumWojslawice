package arboretum.arboretumwojslawice.Commons;


import android.os.Parcel;

import org.parceler.ParcelConverter;
import org.parceler.Parcels;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.Route;

/**
 * Created by Michal on 25.03.2018.
 */

public class CustomParcelConverter implements ParcelConverter<List<Route>> {

    @Override
    public void toParcel(List<Route> input, Parcel parcel) {
        if (input == null) {
            parcel.writeInt(-1);
        }
        else {
            parcel.writeInt(input.size());
            for (Route item : input) {
                parcel.writeParcelable(Parcels.wrap(item), 0);
            }
        }
    }

    @Override
    public List<Route> fromParcel(Parcel parcel) {
        return null;
    }
}
