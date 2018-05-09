package arboretum.arboretumwojslawice.View.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Restaurant;
import arboretum.arboretumwojslawice.View.viewholder.RestaurantViewHolder;
import arboretum.arboretumwojslawice.databinding.RestaurantRowBinding;

/**
 * Created by weronika on 09.05.2018.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private List<Restaurant> restaurants = new ArrayList<>();

    @Inject
    public RestaurantAdapter() {
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RestaurantRowBinding binding = RestaurantRowBinding.inflate(inflater, parent, false);

        switch (viewType) {
            case 0:
                return new RestaurantViewHolder(binding);
            default:
                throw new IllegalArgumentException("This viewType is not supported " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.bind(restaurant);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public void setData(List<Restaurant> restaurant) {
        this.restaurants = restaurant;
        notifyDataSetChanged();
    }
}
