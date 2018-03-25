package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;

import java.util.Date;
import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-25.
 */

public class Route {

    private int idRoute;
    private double length;
    private int time;
    private int mapImage;
    private String name;
    private String description;
    private List<PlantOnRoute> plantsOnRoute;


    public Route(int idRoute, double length, int time, int mapImage, String name, String description, List<PlantOnRoute> plantsOnRoute) {
        this.idRoute = idRoute;
        this.length = length;
        this.time = time;
        this.mapImage = mapImage;
        this.name = name;
        this.description = description;
        this.plantsOnRoute = plantsOnRoute;
    }

    public Route(int idRoute, double length, int time, int mapImage, String name, String description) {
        this.idRoute = idRoute;
        this.length = length;
        this.time = time;
        this.mapImage = mapImage;
        this.name = name;
        this.description = description;
    }

    public Route(List<PlantOnRoute> plantsOnRoute) {
        this.plantsOnRoute = plantsOnRoute;
    }

    public Route(int idRoute, double length, int time, String name) {
        this.idRoute = idRoute;
        this.length = length;
        this.time = time;
        this.name = name;
    }


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

    public int getTimeInt() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getTimeDate() {
        int minutes = time - ((time/100)*100);
        int hour = time/100;
        Date time = new Date();
        time.setHours(hour);
        time.setMinutes(minutes);
        return time;
    }

    public void setTime(Date time) {
        int minutes = time.getMinutes();
        int hour = time.getHours();
        int intTime = hour*100 + minutes;
        this.time = intTime;
    }

    public int getMapImage() {
        return mapImage;
    }

    public void setMapImage(int mapImage) {
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

    public List<PlantOnRoute> getPlantsOnRoute() {
        return plantsOnRoute;
    }

    public void setPlantsOnRoute(List<PlantOnRoute> plantsOnRoute) {
        this.plantsOnRoute = plantsOnRoute;
    }


    @Override
    public String toString() {
        return "(id = " + idRoute + ") " + name + " - " + length + " km, " + getTimeDate().getHours() + ":" + getTimeDate().getMinutes();
    }
    

    public class PlantOnRoute {

        private int pointOrder;
        private Plant plant;
        private double x;
        private double y;

        public PlantOnRoute(int pointOrder) {
            this.pointOrder = pointOrder;
        }

        public PlantOnRoute(Plant plant) {
            this.plant = plant;
        }

        public PlantOnRoute(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public PlantOnRoute(int pointOrder, double x, double y) {
            this.pointOrder = pointOrder;
            this.x = x;
            this.y = y;
        }

        public PlantOnRoute(int pointOrder, Plant plant, double x, double y) {
            this.pointOrder = pointOrder;
            this.plant = plant;
            this.x = x;
            this.y = y;
        }

        public int getPointOrder() {
            return pointOrder;
        }

        public void setPointOrder(int pointOrder) {
            this.pointOrder = pointOrder;
        }

        public Plant getPlant() {
            return plant;
        }

        public void setPlant(Plant plant) {
            this.plant = plant;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }


        @Override
        public String toString() {
            return "(RouteId = " + idRoute + ") nr " + pointOrder + " --> " + plant.getName() + "(id = " + plant.getIdPlant() + ")";
        }
    }

}
