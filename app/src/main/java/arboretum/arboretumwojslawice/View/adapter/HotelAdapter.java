package arboretum.arboretumwojslawice.View.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Hotel;
import arboretum.arboretumwojslawice.View.viewholder.HotelViewHolder;
import arboretum.arboretumwojslawice.databinding.HotelRowBinding;

/**
 * Created by weronika on 09.05.2018.
 */

public class HotelAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private List<Hotel> hotels = new ArrayList<>();

    @Inject
    public HotelAdapter() {
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        HotelRowBinding binding = HotelRowBinding.inflate(inflater, parent, false);

        switch (viewType) {
            case 0:
                return new HotelViewHolder(binding);
            default:
                throw new IllegalArgumentException("This viewType is not supported " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        Hotel hotel = hotels.get(position);
        holder.bind(hotel);
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public void setData(List<Hotel> hotel) {
        this.hotels = hotel;
        notifyDataSetChanged();
    }
}
