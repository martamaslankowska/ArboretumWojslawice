package arboretum.arboretumwojslawice.View.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Attraction;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.View.viewholder.AttractionViewHolder;
import arboretum.arboretumwojslawice.View.viewholder.FavouriteViewHolder;
import arboretum.arboretumwojslawice.databinding.AttractionRowBinding;
import arboretum.arboretumwojslawice.databinding.FavouritesRowBinding;

/**
 * Created by weronika on 08.05.2018.
 */

public class AttractionAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private List<Attraction> attractions = new ArrayList<>();

    @Inject
    public AttractionAdapter() {
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AttractionRowBinding binding = AttractionRowBinding.inflate(inflater, parent, false);

        switch (viewType) {
            case 0:
                return new AttractionViewHolder(binding);
            default:
                throw new IllegalArgumentException("This viewType is not supported " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        Attraction attraction = attractions.get(position);
        holder.bind(attraction);
    }

    @Override
    public int getItemCount() {
        return attractions.size();
    }

    public void setData(List<Attraction> attraction) {
        this.attractions = attraction;
        notifyDataSetChanged();
    }
}
