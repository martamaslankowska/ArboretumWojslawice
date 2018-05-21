package arboretum.arboretumwojslawice.Model.Entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;


@Entity(tableName = "RouteMapCoords",
        foreignKeys = {
                @ForeignKey(entity = RouteEntity.class, parentColumns = "IdRoute", childColumns = "IdRoute"),
                @ForeignKey(entity = RouteEntity.class, parentColumns = "MapImageDetailed", childColumns = "MapImage")})
public class RouteMapCoordEntity {

    @PrimaryKey
    @ColumnInfo(name = "IdRoute")
    @NonNull
    private int idRoute;

    @ColumnInfo(name = "MapImage")
    @NonNull
    private String mapImage;

    @ColumnInfo(name = "MinLat")
    @NonNull
    private double minLat;

    @ColumnInfo(name = "MaxLat")
    @NonNull
    private double maxLat;

    @ColumnInfo(name = "MinLon")
    @NonNull
    private double minLon;

    @ColumnInfo(name = "MaxLon")
    @NonNull
    private double maxLon;


    public RouteMapCoordEntity(int idRoute, String mapImage, double minLat, double maxLat, double minLon, double maxLon) {
        this.idRoute = idRoute;
        this.mapImage = mapImage;
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLon = minLon;
        this.maxLon = maxLon;
    }


    public int getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(int idRoute) {
        this.idRoute = idRoute;
    }

    public String getMapImage() {
        return mapImage;
    }

    public void setMapImage(String mapImage) {
        this.mapImage = mapImage;
    }

    public double getMinLat() {
        return minLat;
    }

    public void setMinLat(double minLat) {
        this.minLat = minLat;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(double maxLat) {
        this.maxLat = maxLat;
    }

    public double getMinLon() {
        return minLon;
    }

    public void setMinLon(double minLon) {
        this.minLon = minLon;
    }

    public double getMaxLon() {
        return maxLon;
    }

    public void setMaxLon(double maxLon) {
        this.maxLon = maxLon;
    }
}
