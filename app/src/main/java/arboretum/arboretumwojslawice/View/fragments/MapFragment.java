package arboretum.arboretumwojslawice.View.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import arboretum.arboretumwojslawice.Commons.map.MapManager;
import arboretum.arboretumwojslawice.R;

/**
 * Created by weronika on 22.03.2018.
 */

public class MapFragment extends Fragment {

    /* map */
    ImageView imageview;
    /* /map */

    //toilets coordinates list ;)


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        imageview = rootView.findViewById(R.id.map);

        // Inflate the layout for this fragment
        return rootView;
    }
}
