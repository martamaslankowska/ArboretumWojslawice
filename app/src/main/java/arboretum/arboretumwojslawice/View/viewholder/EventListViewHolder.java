package arboretum.arboretumwojslawice.View.viewholder;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.additionalEntity.EventRowList;
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
        if (binding.eventName.getText().length() < 30) {
            binding.eventName.setPadding(0, 16, 0, 0);
        }
    }
}
