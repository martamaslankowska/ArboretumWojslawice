package arboretum.arboretumwojslawice.View;

/**
 * Created by Michal on 24.03.2018.
 */

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Route;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.viewholder.RouteListViewHolder;
import arboretum.arboretumwojslawice.databinding.RouteRowBinding;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private List<Route> mRoutes = new ArrayList<>();
    OnItemClickListener listener;

    public CustomAdapter(OnItemClickListener listener){
        this.listener =  listener;
    }


    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        Route route = mRoutes.get(position);
        holder.bind(route);
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


    void setData(List<Route> route) {
        this.mRoutes = route;
        notifyDataSetChanged();
    }
}