package arboretum.arboretumwojslawice.View.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.View.viewholder.PlantListViewHolder;
import arboretum.arboretumwojslawice.databinding.PlantRowBinding;

/**
 * Created by weronika on 04.04.2018.
 */

public class PlantAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private List<Plant> mPlants = new ArrayList<>();
    PlantAdapter.OnItemClickListener listener;

    public PlantAdapter(PlantAdapter.OnItemClickListener listener, List<Plant> pList){

        this.listener =  listener;
        this.mPlants = pList;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PlantRowBinding binding = PlantRowBinding.inflate(inflater, parent, false);

        switch (viewType) {
            case 0:
                return new PlantListViewHolder(binding, listener);
            default:
                throw new IllegalArgumentException("This viewType is not supported " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        Plant plant = mPlants.get(position);
        holder.bind(plant);
    }

    @Override
    public int getItemCount() {
        return mPlants.size();
    }

    public interface OnItemClickListener
    {
        public void onItemClick(int position);
        public void onHeartClick(int position);
    }


    public void setData(List<Plant> plant) {
        this.mPlants = plant;
        notifyDataSetChanged();
    }
}
