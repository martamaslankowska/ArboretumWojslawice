package arboretum.arboretumwojslawice.Commons;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Commons.map.LonLat;
import arboretum.arboretumwojslawice.Model.additionalEntity.EventRowList;
import arboretum.arboretumwojslawice.Model.businessentity.News;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;

public class Globals {

    public static String weatherTemp = "";
    public static String weatherName = "";
    public static News currentNews = null;
    public static EventRowList nearestEvents = null;
    public static Plant seasonPlant = null;
    public static int eventImageResource = 0;
    public static boolean isDownload = false;
    final static String TAG = "Arboretum";

    public static boolean isInternetOn(Context c) {
        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            Log.v(TAG, "Internet is working");
            // txt_status.setText("Internet is working");
            return true;
        } else {
            // txt_status.setText("Internet Connection Not Present");
            Log.v(TAG, "Internet Connection Not Present");
            return false;
        }
    }

    public static boolean isNetworkConnected(Context c) {
        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(c.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();

        // return cm.getActiveNetworkInfo() != null;

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* map */
    public final static double MinLat = 50.708576;
    public final static double MaxLat = 50.713243;
    public final static double MaxLon = 16.867159;
    public final static double MinLon = 16.853841;
    public final static List<LonLat> plantsPlaces = new ArrayList<>();
    /* /map */


}
