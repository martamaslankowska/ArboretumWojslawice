package arboretum.arboretumwojslawice.View.viewholder;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Event;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.View.Adapter.EventAdapter;
import arboretum.arboretumwojslawice.View.Adapter.PlantAdapter;
import arboretum.arboretumwojslawice.databinding.EventRowBinding;
import arboretum.arboretumwojslawice.databinding.PlantRowBinding;

/**
 * Created by weronika on 05.04.2018.
 */

public class EventListViewHolder extends BindingViewHolder<Event, EventRowBinding> {
    public EventListViewHolder(EventRowBinding binding, final EventAdapter.OnItemClickListener listener) {
        super(binding);
        itemView.setOnClickListener(__ -> listener.onItemClick(getAdapterPosition()));
    }

    @Override
    public void bind(Event item) {
        binding.setEvent(item);
        binding.executePendingBindings();
    }
}
