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
public class RouteEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdRoute")
    private int idRoute;

    @ColumnInfo(name = "Length")
    @NonNull
    private double length;

    @ColumnInfo(name = "Time")
    @NonNull
    private int time;

    @ColumnInfo(name = "MapImage")
    private int mapImage;


    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getMapImage() {
        return mapImage;
    }

    public void setMapImage(int mapImage) {
        this.mapImage = mapImage;
    }
}
