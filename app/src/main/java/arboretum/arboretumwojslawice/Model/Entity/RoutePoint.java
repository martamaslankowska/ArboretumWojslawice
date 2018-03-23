package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-23.
 */


@Entity(tableName = "RoutePoints",
        foreignKeys = {
            @ForeignKey(entity = Route.class, parentColumns = "IdRoute", childColumns = "IdRoute"),
            @ForeignKey(entity = Coordinate.class, parentColumns = "IdCoordinate", childColumns = "IdCoordinate")},
        primaryKeys = {"IdRoute", "IdCoordinate"})
public class RoutePoint {

    @ColumnInfo(name = "IdRoute")
    @NonNull
    private int idRoute;

    @ColumnInfo(name = "IdCoordinate")
    @NonNull
    private int idCoordinate;

    @ColumnInfo(name = "PointOrder")
    private int pointOrder;


    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public int getIdCoordinate() {
        return idCoordinate;
    }

    public void setIdCoordinate(int idCoordinate) {
        this.idCoordinate = idCoordinate;
    }

    public int getPointOrder() {
        return pointOrder;
    }

    public void setPointOrder(int pointOrder) {
        this.pointOrder = pointOrder;
    }
}
