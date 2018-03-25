package arboretum.arboretumwojslawice.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.parceler.Parcels;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.Route;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.databinding.ActivityRouteDetailBinding;

public class RouteDetail extends AppCompatActivity {

    private Bundle bundle;
    private int route_id;
    protected List<Route> mRoutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_detail);

        Intent intent = getIntent();
        bundle = intent.getBundleExtra("BUNDLE");
        route_id = bundle.getInt("ROUTE_ID");
        //mRoutes = Parcels.unwrap((Parcelable) bundle.getParcelableArrayList("M_LIST"));

        ActivityRouteDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_route_detail);
        Log.i("mRoutes", String.valueOf(route_id));


        //Route route = (Route) mRoutes.get(route_id);
        //binding.setRoute(route);
    }


//    @Override
//    public void onBackPressed()
//    {
//        Intent intent_out = new Intent();
//        intent_out.putExtra("RatingFromFragment", ff.getRating());
//        intent_out.putExtra("Position", position);
//        setResult(Activity.RESULT_OK, intent_out);
//        Log.i("Zapisana ocena:", String.valueOf(ff.getRating()));
//        finish();
//    }
}
