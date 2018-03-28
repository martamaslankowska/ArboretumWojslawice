package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;

import org.parceler.Parcel;

import java.util.Date;
import java.util.List;

import arboretum.arboretumwojslawice.Commons.AdapterItem;
import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-25.
 */

@Parcel
public class Route implements AdapterItem {

    @ColumnInfo(name = "IdRoute")
    private int idRoute;

    @ColumnInfo(name = "Length")
    private Double length;

    @ColumnInfo(name = "Time")
    private Integer time;

    @ColumnInfo(name = "MapImage")
    private Integer mapImage;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Description")
    private String description;

    //@Ignore
    //private List<PlantOnRoute> plantsOnRoute;


//    @Ignore
//    public Route(Integer idRoute, Double length, Integer time, Integer mapImage, String name, String description, List<PlantOnRoute> plantsOnRoute) {
//        this.idRoute = idRoute;
//        this.length = length;
//        this.time = time;
//        this.mapImage = mapImage;
//        this.name = name;
//        this.description = description;
//        this.plantsOnRoute = plantsOnRoute;
//    }

    public Route(Integer idRoute, Double length, Integer time, Integer mapImage, String name, String description) {
        this.idRoute = idRoute;
        this.length = length;
        this.time = time;
        this.mapImage = mapImage;
        this.name = name;
        this.description = description;
    }

//    @Ignore
//    public Route(List<PlantOnRoute> plantsOnRoute) {
//        this.plantsOnRoute = plantsOnRoute;
//    }

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

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Integer idRoute) {
        this.idRoute = idRoute;
    }

    public Double getLength() {
        return length;
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

    public void setTime(Date time) {
        Integer minutes = time.getMinutes();
        Integer hour = time.getHours();
        Integer IntegerTime = hour*100 + minutes;
        this.time = IntegerTime;
    }

    public Integer getMapImage() {
        return mapImage;
    }

    public void setMapImage(Integer mapImage) {
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
