package arboretum.arboretumwojslawice.View.viewholder;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.AdditionalEntity.EventRowList;
import arboretum.arboretumwojslawice.View.adapter.EventAdapter;
import arboretum.arboretumwojslawice.databinding.EventRowBinding;

public class EventListViewHolder extends BindingViewHolder<EventRowList, EventRowBinding> {
    public EventListViewHolder(EventRowBinding binding, final EventAdapter.OnItemClickListener listener) {
        super(binding);
        itemView.setOnClickListener(__ -> listener.onItemClick(getAdapterPosition()));
    }

    @Override
    public void bind(EventRowList item) {
        binding.setEvent(item);
        binding.executePendingBindings();
    }
}
