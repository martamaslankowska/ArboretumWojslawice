package arboretum.arboretumwojslawice.View.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Price;
import arboretum.arboretumwojslawice.View.viewholder.PriceForTicketsViewHolder;
import arboretum.arboretumwojslawice.databinding.PriceRowBinding;

public class PriceForTicketsAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private List<Price> mPrices = new ArrayList<>();

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PriceRowBinding binding = PriceRowBinding.inflate(inflater, parent, false);
        Log.i("Pierwsza cena", mPrices.get(0).getType());
        switch (viewType) {
            case 0:
                return new PriceForTicketsViewHolder(binding);
            default:
                throw new IllegalArgumentException("This viewType is not supported " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        Price price = mPrices.get(position);
        holder.bind(price);
    }

    @Override
    public int getItemCount()
    {
        return mPrices.size();
    }

    public void setData(List<Price> prices) {
        this.mPrices = prices;
        notifyDataSetChanged();
    }
}
