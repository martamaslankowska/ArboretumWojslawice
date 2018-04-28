package arboretum.arboretumwojslawice.View.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.Price;
import arboretum.arboretumwojslawice.View.viewholder.PriceForServicesViewHolder;
import arboretum.arboretumwojslawice.databinding.PriceServiceRowBinding;

public class PriceForServicesAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private List<Price> mPrices = new ArrayList<>();

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PriceServiceRowBinding binding = PriceServiceRowBinding.inflate(inflater, parent, false);
        switch (viewType) {
            case 0:
                return new PriceForServicesViewHolder(binding);
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
