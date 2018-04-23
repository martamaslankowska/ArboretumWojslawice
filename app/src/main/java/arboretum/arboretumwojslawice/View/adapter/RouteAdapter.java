package arboretum.arboretumwojslawice.View.adapter;

/**
 * Created by Michal on 24.03.2018.
 */

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Route;
import arboretum.arboretumwojslawice.View.viewholder.RouteListViewHolder;
import arboretum.arboretumwojslawice.databinding.RouteRowBinding;

public class RouteAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private List<Route> mRoutes = new ArrayList<>();
    OnItemClickListener listener;
    public int selectedPosition = -1;

    public RouteAdapter(OnItemClickListener listener){
        this.listener =  listener;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        Route route = mRoutes.get(position);
        holder.bind(route);
        if(selectedPosition == position)
            holder.itemView.setBackgroundColor(Color.parseColor("#bee0b8")); // 62b452
        else
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
    }


    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RouteRowBinding binding = RouteRowBinding.inflate(inflater, parent, false);

        switch (viewType) {
            case 0:
                return new RouteListViewHolder(binding, listener);
            default:
                throw new IllegalArgumentException("This viewType is not supported " + viewType);
        }
    }

    @Override
    public int getItemCount()
    {
        return mRoutes.size();
    }

    public interface OnItemClickListener
    {
        public void onItemClick(int position);
        public void onDetailClick(int position);
    }


    public void setData(List<Route> route) {
        this.mRoutes = route;
        notifyDataSetChanged();
    }
}