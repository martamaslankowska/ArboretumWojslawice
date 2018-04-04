package arboretum.arboretumwojslawice.ViewModel;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import arboretum.arboretumwojslawice.Model.MoreOptionItem;
import arboretum.arboretumwojslawice.R;

/**
 * Created by weronika on 04.04.2018.
 */

public class MoreViewModel {
    List<MoreOptionItem> mOptions;
    Context mContext;

    public MoreViewModel(Context context) {
        mContext = context;
    }

    public List<MoreOptionItem> getData() {
        mOptions = new ArrayList<>();
        List<String> Lines = Arrays.asList(mContext.getResources().getStringArray(R.array.more_options));
        for (String item:Lines) {
            mOptions.add(new MoreOptionItem(item));
        }
        return mOptions;
    }
}
