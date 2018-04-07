package arboretum.arboretumwojslawice.View.viewholder;

import arboretum.arboretumwojslawice.Commons.BindingViewHolder;
import arboretum.arboretumwojslawice.Model.businessentity.News;
import arboretum.arboretumwojslawice.databinding.NewsRowBinding;

public class NewsViewHolder extends BindingViewHolder<News, NewsRowBinding> {

    public NewsViewHolder(NewsRowBinding binding) {
        super(binding);
    }

    @Override
    public void bind(News item) {
        binding.setNews(item);
        binding.executePendingBindings();
    }
}
