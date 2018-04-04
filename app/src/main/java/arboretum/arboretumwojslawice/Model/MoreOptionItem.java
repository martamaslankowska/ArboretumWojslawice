package arboretum.arboretumwojslawice.Model;

import arboretum.arboretumwojslawice.Commons.AdapterItem;

/**
 * Created by weronika on 04.04.2018.
 */

public class MoreOptionItem implements AdapterItem {

    private String option;

    public MoreOptionItem(String option) {
        this.option = option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getOption() {

        return option;
    }

    @Override
    public int getItemType() {
        return 1;
    }
}
