package arboretum.arboretumwojslawice.View.viewholder;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Price;
import arboretum.arboretumwojslawice.databinding.PriceTicketRowBinding;

public class PriceForTicketsViewHolder extends BindingViewHolder<Price, PriceTicketRowBinding> {

    public PriceForTicketsViewHolder(PriceTicketRowBinding binding) {
        super(binding);
    }

    @Override
    public void bind(Price item) {
        binding.setPrice(item);
        binding.executePendingBindings();
    }
}
