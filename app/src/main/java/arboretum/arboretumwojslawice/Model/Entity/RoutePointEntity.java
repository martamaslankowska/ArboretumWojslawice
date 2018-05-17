package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-23.
 */


@Entity(tableName = "RoutePoints",
        foreignKeys = {
            @ForeignKey(entity = RouteEntity.class, parentColumns = "IdRoute", childColumns = "IdRoute"),
            @ForeignKey(entity = LocationEntity.class, parentColumns = "IdLocation", childColumns = "IdLocation")},
        primaryKeys = {"IdRoute", "IdLocation"})
//        indices = {@Index(value = {"IdRoute", "IdLocation"}, unique = true)})
public class RoutePointEntity {

    @ColumnInfo(name = "IdRoute")
    @NonNull
    private int idRoute;

    @ColumnInfo(name = "IdLocation")
    @NonNull
    private int idLocation;

    @ColumnInfo(name = "PointOrder")
    private int pointOrder;

    @ColumnInfo(name = "Highlighted")
    private boolean highlighted;



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

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }
}
