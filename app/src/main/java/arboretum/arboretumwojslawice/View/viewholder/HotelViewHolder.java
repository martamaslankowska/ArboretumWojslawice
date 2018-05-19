package arboretum.arboretumwojslawice.View.viewholder;

import android.graphics.Typeface;
import android.view.View;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Hotel;
import arboretum.arboretumwojslawice.databinding.HotelRowBinding;

/**
 * Created by weronika on 09.05.2018.
 */

public class HotelViewHolder extends BindingViewHolder<Hotel, HotelRowBinding> {
    public HotelViewHolder(HotelRowBinding binding) {
        super(binding);
    }

    @Override
    public void bind(Hotel item) {
        binding.setHotel(item);
        binding.executePendingBindings();

        if(item.getRating() == 0.0) {
            binding.ratingBar.setVisibility(View.GONE);
            binding.hotelRatingTextView.setTypeface(binding.hotelRatingTextView.getTypeface(), Typeface.ITALIC);
            binding.hotelRatingTextView.setPadding(16,0,0,12);

        }
    }
}
