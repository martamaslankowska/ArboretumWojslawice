package arboretum.arboretumwojslawice.View;

/**
 * Created by Michal on 24.03.2018.
 */

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
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

    public CustomAdapter(){

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RouteRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.route_row, parent, false);
        return new ViewHolder(binding);
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
        public ViewHolder(RouteRowBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(@NonNull Route route) {
            mBinding.setRoute(route);
            mBinding.executePendingBindings();
        }
    }

    void setData(List<Route> route) {
        this.mRoutes = route;
        notifyDataSetChanged();
    }
}