package arboretum.arboretumwojslawice.View.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Event;
import arboretum.arboretumwojslawice.View.viewholder.EventDetailViewHolder;
import arboretum.arboretumwojslawice.databinding.EventDetailRowBinding;

public class EventDetailAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private List<Event> mEvents = new ArrayList<>();

    public EventDetailAdapter() {}

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        EventDetailRowBinding binding = EventDetailRowBinding.inflate(inflater, parent, false);

        switch (viewType) {
            case 0:
                return new EventDetailViewHolder(binding);
            default:
                throw new IllegalArgumentException("This viewType is not supported " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        Event event = mEvents.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public void setData(List<Event> event) {
        this.mEvents = event;
        notifyDataSetChanged();
    }
}
