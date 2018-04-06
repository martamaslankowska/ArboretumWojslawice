package arboretum.arboretumwojslawice.View.viewholder;

import android.support.annotation.NonNull;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Route;
import arboretum.arboretumwojslawice.View.adapter.RouteAdapter;
import arboretum.arboretumwojslawice.databinding.RouteRowBinding;

/**
 * Created by Michal on 28.03.2018.
 */

public class RouteListViewHolder extends BindingViewHolder<Route, RouteRowBinding> {

    public RouteListViewHolder(RouteRowBinding binding, final RouteAdapter.OnItemClickListener listener) {
        super(binding);
        itemView.setOnClickListener(__ -> listener.onItemClick(getAdapterPosition()));
        binding.routeDetailArrow.setOnClickListener(__ -> listener.onDetailClick(getAdapterPosition()));
    }

    public void bind(@NonNull Route route) {
        binding.setRoute(route);
        binding.executePendingBindings();
    }
}
