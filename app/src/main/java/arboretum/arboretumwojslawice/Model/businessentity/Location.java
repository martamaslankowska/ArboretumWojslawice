package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by Komputer on 2018-03-28.
 */

public class Location {
    @ColumnInfo(name = "IdLocation")
    private Integer idLocation;

    @ColumnInfo(name = "X")
    private Double x;

    @ColumnInfo(name = "Y")
    private Double y;


    public Location(Integer idLocation, Double x, Double y) {
        this.idLocation = idLocation;
        this.x = x;
        this.y = y;
    }

    public Integer getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(Integer idLocation) {
        this.idLocation = idLocation;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }


    @Override
    public String toString() {
        return "(id = " + idLocation + ") x = " + x + ", y = " + y;
    }
}
