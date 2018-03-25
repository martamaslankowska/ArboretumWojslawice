package arboretum.arboretumwojslawice.Model.businessentity;

import java.util.Date;
import java.util.List;

/**
 * Created by Komputer on 2018-03-25.
 */

public class Plant {

    private int idPlant;
    private String genusName;
    private String speciesName;
    private String name;
    private String kindName;
    private int image;
    private int seasonBegin;
    private int seasonEnd;
    private String description;
    private List<Location> locations;


    public Plant() {
    }  // default constructor for FavouritePlant to handle

    public Plant(int idPlant, String genusName, String speciesName, String name, String kindName, int image, int seasonBegin, int seasonEnd, String description, List<Location> locations) {
        this.idPlant = idPlant;
        this.genusName = genusName;
        this.speciesName = speciesName;
        this.name = name;
        this.kindName = kindName;
        this.image = image;
        this.seasonBegin = seasonBegin;
        this.seasonEnd = seasonEnd;
        this.description = description;
        this.locations = locations;
    }

    // needed for Dao to get all plants
    public Plant(int idPlant, String genusName, String speciesName, String name, String kindName, int image, int seasonBegin, int seasonEnd, String description) {
        this.idPlant = idPlant;
        this.genusName = genusName;
        this.speciesName = speciesName;
        this.name = name;
        this.kindName = kindName;
        this.image = image;
        this.seasonBegin = seasonBegin;
        this.seasonEnd = seasonEnd;
        this.description = description;
    }

    public Plant(List<Location> locations) {
        this.locations = locations;
    }

    public Plant(int idPlant, String genusName, String kindName) {
        this.idPlant = idPlant;
        this.genusName = genusName;
        this.kindName = kindName;
    }

    public int getIdPlant() {
        return idPlant;
    }

    public void setIdPlant(int idPlant) {
        this.idPlant = idPlant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenusName() {
        return genusName;
    }

    public void setGenusName(String genusName) {
        this.genusName = genusName;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getSeasonBeginInt() {
        return seasonBegin;
    }

    public void setSeasonBegin(int seasonBegin) {
        this.seasonBegin = seasonBegin;
    }

    public Date getSeasonBeginDate() {
        int day = seasonBegin - ((seasonBegin / 100) * 100);
        int month = (seasonBegin / 100) - ((seasonBegin / 10000) * 100);
        int year = seasonBegin / 10000;
        Date date = new Date(year, month, day);
        return date;
    }

    public void setSeasonBegin(Date seasonBegin) {
        int day = seasonBegin.getDay();
        int month = seasonBegin.getMonth();
        int year = seasonBegin.getYear();
        int intDate = 10000 * year + 100 * month + day;
        this.seasonBegin = intDate;
    }

    public int getSeasonEndInt() {
        return seasonEnd;
    }

    public void setSeasonEnd(int seasonEnd) {
        this.seasonEnd = seasonEnd;
    }

    public Date getSeasonEndDate() {
        int day = seasonEnd - ((seasonEnd / 100) * 100);
        int month = (seasonEnd / 100) - ((seasonEnd / 10000) * 100);
        int year = seasonEnd / 10000;
        Date date = new Date(year, month, day);
        return date;
    }

    public void setSeasonEnd(Date seasonEnd) {
        int day = seasonEnd.getDay();
        int month = seasonEnd.getMonth();
        int year = seasonEnd.getYear();
        int intDate = 10000 * year + 100 * month + day;
        this.seasonEnd = intDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "(id = " + idPlant + ") name: " + name + " (genus: " + genusName + ") --> " + kindName;
    }


    public class Location {
        private int idLocation;
        private double x;
        private double y;


        public Location(int idLocation, double x, double y) {
            this.idLocation = idLocation;
            this.x = x;
            this.y = y;
        }

        public int getIdLocation() {
            return idLocation;
        }

        public void setIdLocation(int idLocation) {
            this.idLocation = idLocation;
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
            return "(id = " + idLocation + ") x = " + x + ", y = " + y;
        }
    }

}
