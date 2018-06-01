package arboretum.arboretumwojslawice.View.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.businessentity.NewsImage;
import arboretum.arboretumwojslawice.R;

public class NewsAdapter extends BaseAdapter {

    private List<NewsImage> images;
    private Context context;

    @Inject
    public NewsAdapter() { }

    // 1
    public NewsAdapter(Context context, List<NewsImage> images) {
        this.context = context;
        this.images = images;
    }

    // 2
    @Override
    public int getCount() {
        return images.size();
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsImage newsImage = images.get(position);
        ImageView imageView;

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float width = displayMetrics.widthPixels;

        if (convertView == null) {
//            final LayoutInflater layoutInflater = LayoutInflater.from(context);
//            convertView = layoutInflater.inflate(R.layout.news_grid, null);
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams((int)(width *0.32), (int)(width *0.32)));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(0, 8, 0, 8);
        } else {
//            imageView = convertView.findViewById(R.id.gridImageView);
            imageView = (ImageView) convertView;
        }

        int imageRes = newsImage.getImageId(context);
        imageView.setImageResource(imageRes);
        return imageView;
    }


}
