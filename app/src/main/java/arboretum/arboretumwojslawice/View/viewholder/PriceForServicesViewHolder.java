package arboretum.arboretumwojslawice.View.viewholder;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Price;
import arboretum.arboretumwojslawice.databinding.PriceServiceRowBinding;

public class PriceForServicesViewHolder extends BindingViewHolder<Price, PriceServiceRowBinding> {

    public PriceForServicesViewHolder(PriceServiceRowBinding binding) {
        super(binding);
    }

    @Override
    public void bind(Price item) {
        binding.setPrice(item);
        binding.executePendingBindings();
    }
}
