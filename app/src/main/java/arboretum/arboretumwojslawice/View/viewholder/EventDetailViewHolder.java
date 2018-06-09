package arboretum.arboretumwojslawice.View.viewholder;

import android.view.View;

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

        if(item.getDescription() == null || item.getDescription().isEmpty()) {
            binding.eventDetailDescription.setVisibility(View.GONE);
        }

        if(item.getName() == null || item.getName().isEmpty()) {
            binding.eventDetailName.setVisibility(View.GONE);
        }

        if(item.getDateEnd() == 0) {
            binding.eventDetailManyDaysTextView.setVisibility(View.GONE);
        }

        if(item.getTimeEnd() == 0){
            binding.eventDetailEndHour.setVisibility(View.GONE);
        }

        if(item.getTimeBegin() == 0) {
            binding.eventDetailStartHour.setVisibility(View.GONE);
        }
    }
}
