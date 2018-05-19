package arboretum.arboretumwojslawice.View.viewholder;

import android.graphics.Typeface;
import android.view.View;

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

        if(item.noWebsite())
        {
            binding.restaurantWebsiteImage.setVisibility(View.GONE);
            binding.restaurantWebsite.setVisibility(View.GONE);
        }

        if(item.getRating() == 0.0) {
            binding.ratingBar.setVisibility(View.GONE);
            binding.restaurantRatingTextView.setTypeface(binding.restaurantRatingTextView.getTypeface(), Typeface.ITALIC);
            binding.restaurantRatingTextView.setPadding(16,0,0,12);
        }
    }
}
