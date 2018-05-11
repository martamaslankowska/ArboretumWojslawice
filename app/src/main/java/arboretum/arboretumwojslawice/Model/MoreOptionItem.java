package arboretum.arboretumwojslawice.Model;

import android.content.Context;

import arboretum.arboretumwojslawice.Commons.AdapterItem;

/**
 * Created by weronika on 04.04.2018.
 */

public class MoreOptionItem implements AdapterItem {

    private String option;
    private String imageName;

    public MoreOptionItem(String option) {
        this.option = option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }

    public Integer getImageId(Context c) {
        return c.getResources().getIdentifier("arboretum.arboretumwojslawice:drawable/" + imageName, null, null);
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
