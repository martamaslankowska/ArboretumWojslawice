package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.AdapterItem;

/**
 * Created by Komputer on 2018-03-25.
 */

public class Route implements AdapterItem {

    @ColumnInfo(name = "IdRoute")
    private Integer idRoute;

    @ColumnInfo(name = "Length")
    private Double length;

    @ColumnInfo(name = "Time")
    private Integer time;

    @ColumnInfo(name = "MapImage")
    private String mapImage;

    @ColumnInfo(name = "MapImageDetailed")
    private String mapImageDetailed;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Description")
    private String description;


    public Route(Integer idRoute, Double length, Integer time, String mapImage, String mapImageDetailed, String name, String description) {
        this.idRoute = idRoute;
        this.length = length;
        this.time = time;
        this.mapImage = mapImage;
        this.mapImageDetailed = mapImageDetailed;
        this.name = name;
        this.description = description;
    }

//    @Ignore
//    public Route(List<PlantOnRoute> plantsOnRoute) {
//        this.plantsOnRoute = plantsOnRoute;
//    }

    @Ignore
    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @Ignore
    public Route(Integer idRoute, Double length, Integer time, String name) {
        this.idRoute = idRoute;
        this.length = length;
        this.time = time;
        this.name = name;
    }

    @Ignore
    public Route(Integer idRoute, String name, String description) {
        this.idRoute = idRoute;
        this.name = name;
        this.description = description;
    }

    @Ignore
    public Route() {
    }


    public String getStringId() { return String.valueOf(idRoute);}

    public Integer getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Integer idRoute) {
        this.idRoute = idRoute;
    }

    public Double getLength() {
        return length;
    }

    public String getLengthString() {
        return length.toString() + " km";
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Date getTimeDate() {
        Integer minutes = time - ((time/100)*100);
        Integer hour = time/100;
        Date time = new Date();
        time.setHours(hour);
        time.setMinutes(minutes);
        return time;
    }

    public String getTimeString() {
        Date time = getTimeDate();
        String stringTime = time.getHours() + ":" + time.getMinutes();
        return stringTime;
    }

    public void setTime(Date time) {
        Integer minutes = time.getMinutes();
        Integer hour = time.getHours();
        Integer IntegerTime = hour*100 + minutes;
        this.time = IntegerTime;
    }

    public String getMapImage() {
        return mapImage;
    }

    public int getMapImageId(Context c) {
        return c.getResources().getIdentifier("arboretum.arboretumwojslawice:drawable/" + mapImage, null, null);
    }

    public String getMapString(Context c) {
        return String.valueOf(getMapImageId(c));
    }

    public void setMapImage(String mapImage) {
        this.mapImage = mapImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMapImageDetailed() {
        return mapImageDetailed;
    }

    public void setMapImageDetailed(String mapImageDetailed) {
        this.mapImageDetailed = mapImageDetailed;
    }

    public int getMadImageDetailedId(Context c) {
        return c.getResources().getIdentifier("arboretum.arboretumwojslawice:drawable/" + mapImageDetailed, null, null);
    }

    //    public List<PlantOnRoute> getPlantsOnRoute() {
//        return plantsOnRoute;
//    }
//
//    public void setPlantsOnRoute(List<PlantOnRoute> plantsOnRoute) {
//        this.plantsOnRoute = plantsOnRoute;
//    }


    @Override
    public String toString() {
        return "(id = " + idRoute + ") " + name + " - " + length + " km, " + getTimeDate().getHours() + ":" + getTimeDate().getMinutes();
    }

    @Override
    public int getItemType() {
        return 0;
    }


}
