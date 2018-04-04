package arboretum.arboretumwojslawice.View;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;

/**
 * Created by Michal on 04.04.2018.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Plant> mPlants;


    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    public ViewPagerAdapter(Context context, List<Plant> pList) {
        this.context = context;
        this.mPlants = pList;
    }

    @Override
    public int getCount() {
        return mPlants.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.plant_page, null);

        Plant plant = mPlants.get(position);

        ImageView imageView = (ImageView) view.findViewById(R.id.plant_page_image);
        imageView.setImageResource(plant.getImage());
        TextView textView1 = (TextView) view.findViewById(R.id.plant_page_genus);
        textView1.setText(plant.getGenusName());
        TextView textView2 = (TextView) view.findViewById(R.id.plant_page_kind);
        textView2.setText(plant.getKindName());


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
