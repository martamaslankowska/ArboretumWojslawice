package arboretum.arboretumwojslawice.ViewModel;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.di.AppController;
import arboretum.arboretumwojslawice.Model.MoreOptionItem;
import arboretum.arboretumwojslawice.R;

/**
 * Created by weronika on 04.04.2018.
 */

public class MoreViewModel {
    private List<MoreOptionItem> mOptions;
    private Context mContext;

    @Inject
    public MoreViewModel(AppController appController) {
        mContext = appController.getBaseContext();
    }

    public List<MoreOptionItem> getData() {
        mOptions = new ArrayList<>();
        List<String> options = Arrays.asList(mContext.getResources().getStringArray(R.array.more_options));
        for (String item : options) {
            mOptions.add(new MoreOptionItem(item));
        }

        List<String> options_images = Arrays.asList(mContext.getResources().getStringArray(R.array.more_options_images));
        for (int i=0; i<options_images.size(); i++) {
            mOptions.get(i).setImageName(options_images.get(i));
            Log.i("MoreViewModel", options_images.get(i));
        }
        return mOptions;
    }
}
