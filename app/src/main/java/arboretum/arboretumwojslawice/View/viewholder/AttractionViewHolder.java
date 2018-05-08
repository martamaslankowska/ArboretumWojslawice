package arboretum.arboretumwojslawice.View.viewholder;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Attraction;
import arboretum.arboretumwojslawice.databinding.AttractionRowBinding;

/**
 * Created by weronika on 08.05.2018.
 */

public class AttractionViewHolder extends BindingViewHolder<Attraction, AttractionRowBinding> {
    public AttractionViewHolder(AttractionRowBinding binding) {
        super(binding);
    }

    @Override
    public void bind(Attraction item) {
        binding.setAttraction(item);
        binding.executePendingBindings();
    }
}
