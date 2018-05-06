package arboretum.arboretumwojslawice.View.viewholder;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.View.adapter.FavouritesAdapter;
import arboretum.arboretumwojslawice.View.adapter.PlantAdapter;
import arboretum.arboretumwojslawice.databinding.FavouritesRowBinding;
import arboretum.arboretumwojslawice.databinding.PlantRowBinding;

/**
 * Created by weronika on 06.05.2018.
 */

public class FavouriteViewHolder extends BindingViewHolder<Plant, FavouritesRowBinding> {
    public FavouriteViewHolder(FavouritesRowBinding binding, final FavouritesAdapter.OnItemClickListener listener) {
        super(binding);
        itemView.setOnClickListener(__ -> listener.onItemClick(getAdapterPosition()));
    }

    @Override
    public void bind(Plant item) {
        binding.setFavourite(item);
        binding.executePendingBindings();
    }
}
