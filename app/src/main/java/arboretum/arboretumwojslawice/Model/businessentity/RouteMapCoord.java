package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;

public class RouteMapCoord {

    @ColumnInfo(name = "IdRoute")
    private Integer idRoute;

    @ColumnInfo(name = "MapImage")
    private String mapImage;

    @ColumnInfo(name = "MinLat")
    private Double minLat;

    @ColumnInfo(name = "MaxLat")
    private Double maxLat;

    @ColumnInfo(name = "MinLon")
    private Double minLon;

    @ColumnInfo(name = "MaxLon")
    private Double maxLon;


    public RouteMapCoord(Integer idRoute, String mapImage, Double minLat, Double maxLat, Double minLon, Double maxLon) {
        this.idRoute = idRoute;
        this.mapImage = mapImage;
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLon = minLon;
        this.maxLon = maxLon;
    }


    @Ignore
    public RouteMapCoord(Integer idRoute, Double minLat, Double maxLat, Double minLon, Double maxLon) {
        this.idRoute = idRoute;
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLon = minLon;
        this.maxLon = maxLon;
    }

    @Ignore
    public RouteMapCoord(Integer idRoute) {
        this.idRoute = idRoute;
    }

    public Integer getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Integer idRoute) {
        this.idRoute = idRoute;
    }

    public String getMapImage() {
        return mapImage;
    }

    public void setMapImage(String mapImage) {
        this.mapImage = mapImage;
    }

    public Double getMinLat() {
        return minLat;
    }

    public void setMinLat(Double minLat) {
        this.minLat = minLat;
    }

    public Double getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(Double maxLat) {
        this.maxLat = maxLat;
    }

    public Double getMinLon() {
        return minLon;
    }

    public void setMinLon(Double minLon) {
        this.minLon = minLon;
    }

    public Double getMaxLon() {
        return maxLon;
    }

    public void setMaxLon(Double maxLon) {
        this.maxLon = maxLon;
    }


}
