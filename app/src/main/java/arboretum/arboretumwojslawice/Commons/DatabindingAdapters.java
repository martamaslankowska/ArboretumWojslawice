package arboretum.arboretumwojslawice.Commons;

import android.databinding.BindingAdapter;
import android.support.constraint.ConstraintLayout;
import android.view.View;


import arboretum.arboretumwojslawice.R;

public class DatabindingAdapters {
    
    @BindingAdapter("app:layout_constraintBottom_toTopOf")
    public static void eventBindingAdapterBottomToTop(View view, int id_object)
    {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        params.bottomToTop = id_object;
        view.setLayoutParams(params);
    }

    @BindingAdapter("app:layout_constraintBottom_toBottomOf")
    public static void eventBindingAdapterBottomToBottom(View view, int id_object)
    {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        params.bottomToBottom = id_object;
        view.setLayoutParams(params);
    }

    @BindingAdapter("app:layout_constraintTop_toBottomOf")
    public static void eventBindingAdapterTopToBottom(View view, int id_object)
    {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        params.topToBottom = id_object;
        view.setLayoutParams(params);
    }
}
