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
    private List<MoreOptionItem> options;
    private Context context;

    @Inject
    public MoreViewModel(AppController appController) {
        context = appController.getBaseContext();
    }

    public List<MoreOptionItem> getData() {
        options = new ArrayList<>();
        List<String> options = Arrays.asList(context.getResources().getStringArray(R.array.more_options));
        for (String item : options) {
            this.options.add(new MoreOptionItem(item));
        }

        List<String> options_images = Arrays.asList(context.getResources().getStringArray(R.array.more_options_images));
        for (int i=0; i<options_images.size(); i++) {
            this.options.get(i).setImageName(options_images.get(i));
            Log.i("MoreViewModel", options_images.get(i));
        }
        return this.options;
    }
}
