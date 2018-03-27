package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;

import java.util.Date;
import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-25.
 */

public class Route {

    private Integer idRoute;
    private Double length;
    private Integer time;
    private Integer mapImage;
    private String name;
    private String description;
    @Ignore
    private List<PlantOnRoute> plantsOnRoute;


    @Ignore
    public Route(Integer idRoute, Double length, Integer time, Integer mapImage, String name, String description, List<PlantOnRoute> plantsOnRoute) {
        this.idRoute = idRoute;
        this.length = length;
        this.time = time;
        this.mapImage = mapImage;
        this.name = name;
        this.description = description;
        this.plantsOnRoute = plantsOnRoute;
    }

    public Route(Integer idRoute, Double length, Integer time, Integer mapImage, String name, String description) {
        this.idRoute = idRoute;
        this.length = length;
        this.time = time;
        this.mapImage = mapImage;
        this.name = name;
        this.description = description;
    }

    @Ignore
    public Route(List<PlantOnRoute> plantsOnRoute) {
        this.plantsOnRoute = plantsOnRoute;
    }

    @Ignore
    public Route(Integer idRoute, Double length, Integer time, String name) {
        this.idRoute = idRoute;
        this.length = length;
        this.time = time;
        this.name = name;
    }


    public Integer getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Integer idRoute) {
        this.idRoute = idRoute;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Date getTimeDate() {
        Integer minutes = time - ((time/100)*100);
        Integer hour = time/100;
        Date time = new Date();
        time.setHours(hour);
        time.setMinutes(minutes);
        return time;
    }

    public void setTime(Date time) {
        Integer minutes = time.getMinutes();
        Integer hour = time.getHours();
        Integer IntegerTime = hour*100 + minutes;
        this.time = IntegerTime;
    }

    public Integer getMapImage() {
        return mapImage;
    }

    public void setMapImage(Integer mapImage) {
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

        private Integer pointOrder;
        @Ignore
        private Plant plant;
        private Double x;
        private Double y;

        @Ignore
        public PlantOnRoute(Integer pointOrder) {
            this.pointOrder = pointOrder;
        }

        @Ignore
        public PlantOnRoute(Plant plant) {
            this.plant = plant;
        }

        @Ignore
        public PlantOnRoute(Double x, Double y) {
            this.x = x;
            this.y = y;
        }

        public PlantOnRoute(Integer pointOrder, Double x, Double y) {
            this.pointOrder = pointOrder;
            this.x = x;
            this.y = y;
        }

        public PlantOnRoute(Integer pointOrder, Plant plant, Double x, Double y) {
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


        @Override
        public String toString() {
            return "(RouteId = " + idRoute + ") nr " + pointOrder + " --> " + plant.getName() + "(id = " + plant.getIdPlant() + ")";
        }
    }

}
