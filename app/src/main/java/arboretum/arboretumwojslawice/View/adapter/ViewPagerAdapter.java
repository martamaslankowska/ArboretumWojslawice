package arboretum.arboretumwojslawice.View.adapter;

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
import arboretum.arboretumwojslawice.Model.businessentity.PointOnRoute;
import arboretum.arboretumwojslawice.R;

/**
 * Created by Michal on 04.04.2018.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Plant> mPlants;
    View.OnClickListener listener;

    public ViewPagerAdapter(Context context, View.OnClickListener listener) {
        this.context = context;
        this.listener = listener;
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

        ImageView imageView = view.findViewById(R.id.plant_page_image);
        imageView.setImageResource(plant.getImageId(context));
        TextView name_textView = view.findViewById(R.id.plant_page_name);
        name_textView.setText(plant.getName());
        TextView genus_textView = view.findViewById(R.id.plant_page_genus);
        genus_textView.setText(plant.getGenusName());

        ViewPager vp = (ViewPager) container;
        view.setOnClickListener(listener);
        vp.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    public interface OnItemClickListener extends View.OnClickListener {
        void onClick(View view);
    }

    public void setData(List<Plant> mPlants) {
        this.mPlants = mPlants;
    }
}
