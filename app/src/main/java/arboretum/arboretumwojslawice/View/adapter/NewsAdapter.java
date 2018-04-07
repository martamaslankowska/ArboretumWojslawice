package arboretum.arboretumwojslawice.View.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.News;
import arboretum.arboretumwojslawice.View.viewholder.NewsViewHolder;
import arboretum.arboretumwojslawice.databinding.NewsRowBinding;

public class NewsAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private List<News> mOptions;

    public NewsAdapter() {
    }


    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        NewsRowBinding binding = NewsRowBinding.inflate(inflater, parent, false);

        switch (viewType) {
            case 0:
                return new NewsViewHolder(binding);
            default:
                throw new IllegalArgumentException("This viewType is not supported " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        News option = mOptions.get(position);
        holder.bind(option);
    }

    @Override
    public int getItemCount() {
        return mOptions.size();
    }

    public interface OnItemClickListener
    {
        public void onItemClick(int position);
    }

    public void setData(List<News> option) {
        this.mOptions = option;
        notifyDataSetChanged();
    }
}
