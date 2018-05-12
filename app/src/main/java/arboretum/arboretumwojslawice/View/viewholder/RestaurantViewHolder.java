package arboretum.arboretumwojslawice.View.viewholder;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Restaurant;
import arboretum.arboretumwojslawice.databinding.RestaurantRowBinding;

/**
 * Created by weronika on 09.05.2018.
 */

public class RestaurantViewHolder extends BindingViewHolder<Restaurant, RestaurantRowBinding> {
    public RestaurantViewHolder(RestaurantRowBinding binding) {
        super(binding);
    }

    @Override
    public void bind(Restaurant item) {
        binding.setRestaurant(item);
        binding.executePendingBindings();
    }
}