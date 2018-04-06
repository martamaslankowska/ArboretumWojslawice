package arboretum.arboretumwojslawice.View.viewholder;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.View.adapter.PlantAdapter;
import arboretum.arboretumwojslawice.databinding.PlantRowBinding;

/**
 * Created by weronika on 04.04.2018.
 */

public class PlantListViewHolder extends BindingViewHolder<Plant, PlantRowBinding> {

    public PlantListViewHolder(PlantRowBinding binding, final PlantAdapter.OnItemClickListener listener) {
        super(binding);
        itemView.setOnClickListener(__ -> listener.onItemClick(getAdapterPosition()));
    }

    @Override
    public void bind(Plant item) {
        binding.setPlant(item);
        binding.executePendingBindings();
    }
}
