package arboretum.arboretumwojslawice.Commons;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Michal on 28.03.2018.
 */

public abstract class BindingViewHolder<T extends AdapterItem, K extends ViewDataBinding> extends ViewHolder<T> {
    protected K binding;
    public BindingViewHolder(K binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
abstract class ViewHolder<T extends AdapterItem> extends RecyclerView.ViewHolder {
    protected final Context context;
    public ViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
    }
    public abstract void bind(T item);
}

