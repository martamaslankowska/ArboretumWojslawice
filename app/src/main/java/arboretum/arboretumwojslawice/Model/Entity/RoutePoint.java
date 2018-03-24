package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-23.
 */


@Entity(tableName = "RoutePoints",
        foreignKeys = {
            @ForeignKey(entity = Route.class, parentColumns = "IdRoute", childColumns = "IdRoute"),
            @ForeignKey(entity = Location.class, parentColumns = "IdLocation", childColumns = "IdLocation")},
        primaryKeys = {"IdRoute", "IdLocation"})
public class RoutePoint {

    @ColumnInfo(name = "IdRoute")
    @NonNull
    private int idRoute;

    @ColumnInfo(name = "IdLocation")
    @NonNull
    private int idLocation;

    @ColumnInfo(name = "PointOrder")
    private int pointOrder;


    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public int getPointOrder() {
        return pointOrder;
    }

    public void setPointOrder(int pointOrder) {
        this.pointOrder = pointOrder;
    }
}
