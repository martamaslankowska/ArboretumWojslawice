package arboretum.arboretumwojslawice.View.viewholder;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.MoreOptionItem;
import arboretum.arboretumwojslawice.View.adapter.MoreAdapter;
import arboretum.arboretumwojslawice.databinding.MoreRowBinding;

/**
 * Created by weronika on 04.04.2018.
 */

public class MoreViewHolder extends BindingViewHolder<MoreOptionItem, MoreRowBinding> {

    public MoreViewHolder(MoreRowBinding binding, final MoreAdapter.OnItemClickListener listener) {
        super(binding);
        itemView.setOnClickListener(__ -> listener.onItemClick(getAdapterPosition()));

    }

    @Override
    public void bind(MoreOptionItem item) {
        binding.setMore(item);
        binding.executePendingBindings();
    }
}
