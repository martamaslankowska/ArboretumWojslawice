package arboretum.arboretumwojslawice.Model.Entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

import java.time.Duration;

/**
 * Created by Komputer on 2018-03-23.
 */


@Entity(tableName = "Routes")
public class Route {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdRoute")
    private int idRoute;

    @ColumnInfo(name = "Name")
    @NonNull
    private String name;

    @ColumnInfo(name = "Length")
    @NonNull
    private double length;

    @ColumnInfo(name = "Time")
    @NonNull
    private Duration time;

    @ColumnInfo(name = "Description")
    private String description;

    @ColumnInfo(name = "MapImage")
    private int mapImage;

    public Route(String name, String desc) {
       this.name = name;
       this.description = desc;
    }

    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Duration getTime() {
        return time;
    }

    public void setTime(Duration time) {
        this.time = time;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMapImage() {
        return mapImage;
    }

    public void setMapImage(int mapImage) {
        this.mapImage = mapImage;
    }
}
