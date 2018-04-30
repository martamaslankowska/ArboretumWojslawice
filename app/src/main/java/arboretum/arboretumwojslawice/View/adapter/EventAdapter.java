package arboretum.arboretumwojslawice.View.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Event;
import arboretum.arboretumwojslawice.View.viewholder.EventListViewHolder;
import arboretum.arboretumwojslawice.databinding.EventRowBinding;

/**
 * Created by weronika on 05.04.2018.
 */

public class EventAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private List<Event> mEvents = new ArrayList<>();
    private EventAdapter.OnItemClickListener listener;

    public EventAdapter(EventAdapter.OnItemClickListener listener) {
        this.listener =  listener;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        EventRowBinding binding = EventRowBinding.inflate(inflater, parent, false);

        switch (viewType) {
            case 0:
                return new EventListViewHolder(binding, listener);
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

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setData(List<Event> event) {
        this.mEvents = event;
        notifyDataSetChanged();
    }
}
