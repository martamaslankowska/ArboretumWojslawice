package arboretum.arboretumwojslawice.View.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.MoreOptionItem;
import arboretum.arboretumwojslawice.View.viewholder.MoreViewHolder;
import arboretum.arboretumwojslawice.databinding.MoreRowBinding;

/**
 * Created by weronika on 04.04.2018.
 */

public class MoreAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private List<MoreOptionItem> mOptions = new ArrayList<>();
    OnItemClickListener listener;

    @Inject
    public MoreAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MoreRowBinding binding = MoreRowBinding.inflate(inflater, parent, false);

        switch (viewType) {
            case 0:
                return new MoreViewHolder(binding, listener);
            default:
                throw new IllegalArgumentException("This viewType is not supported " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        MoreOptionItem option = mOptions.get(position);
        holder.bind(option);
    }

    @Override
    public int getItemCount() {
        return mOptions.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setData(List<MoreOptionItem> option) {
        this.mOptions = option;
        notifyDataSetChanged();
    }
}
