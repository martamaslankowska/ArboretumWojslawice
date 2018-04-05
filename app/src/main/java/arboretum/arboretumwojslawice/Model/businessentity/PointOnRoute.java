package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;

/**
 * Created by Komputer on 2018-03-28.
 */


public class PointOnRoute {

    @ColumnInfo(name = "PointOrder")
    private Integer pointOrder;

    @ColumnInfo(name = "IdPlant")
    private Integer idPlant;

    @Ignore
    private Plant plant;

    @ColumnInfo(name = "X")
    private Double x;

    @ColumnInfo(name = "Y")
    private Double y;


    @Ignore
    public PointOnRoute(Integer pointOrder) {
        this.pointOrder = pointOrder;
    }

    @Ignore
    public PointOnRoute(Plant plant) {
        this.plant = plant;
    }

    @Ignore
    public PointOnRoute(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public PointOnRoute(Integer pointOrder, Integer idPlant, Double x, Double y) {
        this.pointOrder = pointOrder;
        this.idPlant = idPlant;
        this.x = x;
        this.y = y;
    }

    @Ignore
    public PointOnRoute(Integer pointOrder, Plant plant, Double x, Double y) {
        this.pointOrder = pointOrder;
        this.plant = plant;
        this.x = x;
        this.y = y;
    }

    public Integer getPointOrder() {
        return pointOrder;
    }

    public void setPointOrder(Integer pointOrder) {
        this.pointOrder = pointOrder;
    }

    public Integer getIdPlant() {
        return idPlant;
    }

    public void setIdPlant(Integer idPlant) {
        this.idPlant = idPlant;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
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


//    @Override
//    public String toString() {
//        return "(RouteId = " + idRoute + ") nr " + pointOrder + " --> " + plant.getName() + "(id = " + plant.getIdPlant() + ")";
//    }
}
