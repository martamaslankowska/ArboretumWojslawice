package arboretum.arboretumwojslawice.View;

/**
 * Created by Michal on 24.03.2018.
 */

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.Route;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.databinding.RouteRowBinding;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Route> mRoutes = new ArrayList<>();
    AdapterView.OnItemClickListener mItemClickListener;

    OnItemClickListener listener;

    public CustomAdapter(OnItemClickListener listener){
        this.listener =  listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RouteRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.route_row, parent, false);
        return new ViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Route route = mRoutes.get(position);
        holder.bind(route);
    }

    @Override
    public int getItemCount()
    {
        return mRoutes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RouteRowBinding mBinding;

        public ViewHolder(RouteRowBinding binding, final OnItemClickListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            itemView.setOnClickListener(__ -> listener.onItemClick(getAdapterPosition()));
        }

        public void bind(@NonNull Route route) {
            mBinding.setRoute(route);
            mBinding.executePendingBindings();
        }
    }

    public interface OnItemClickListener
    {
        public void onItemClick(int position);
    }

    public void SetOnItemClickListener(OnItemClickListener mItemClickListener)
    {
        this.mItemClickListener = (AdapterView.OnItemClickListener) mItemClickListener;
    }

    void setData(List<Route> route) {
        this.mRoutes = route;
        notifyDataSetChanged();
    }
}