package arboretum.arboretumwojslawice.View.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.View.viewholder.FavouriteViewHolder;
import arboretum.arboretumwojslawice.databinding.FavouritesRowBinding;

/**
 * Created by weronika on 06.05.2018.
 */

public class FavouritesAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private List<Plant> mPlants = new ArrayList<>();
    OnItemClickListener listener;

    //@Inject
    public FavouritesAdapter(OnItemClickListener listener) {
            this.listener =  listener;
            }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            FavouritesRowBinding binding = FavouritesRowBinding.inflate(inflater, parent, false);

            switch (viewType) {
            case 0:
                return new FavouriteViewHolder(binding, listener);
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

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setData(List<Plant> plant) {
        this.mPlants = plant;
        notifyDataSetChanged();
    }
}
