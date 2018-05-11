package arboretum.arboretumwojslawice.View.viewholder;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Event;
import arboretum.arboretumwojslawice.databinding.EventDetailRowBinding;

public class EventDetailViewHolder extends BindingViewHolder<Event, EventDetailRowBinding> {
    public EventDetailViewHolder(EventDetailRowBinding binding) {
        super(binding);
    }

    @Override
    public void bind(Event item) {
        binding.setEvent(item);
        binding.executePendingBindings();
    }
}
