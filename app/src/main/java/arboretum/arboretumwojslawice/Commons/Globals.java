package arboretum.arboretumwojslawice.Commons;

import android.app.Application;

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

}
