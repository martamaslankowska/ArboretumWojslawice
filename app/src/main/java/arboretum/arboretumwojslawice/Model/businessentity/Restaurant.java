package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.Ignore;

/**
 * Created by Komputer on 2018-03-25.
 */

public class Restaurant {

    private Integer idRestaurant;
    private String name;
    private String address;
    private Integer phone;
    private String website;
    private Double distance;
    private Double rating;

    public Restaurant(Integer idRestaurant, String name, String address, Integer phone, String website, Double distance, Double rating) {
        this.idRestaurant = idRestaurant;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.distance = distance;
        this.rating = rating;
    }

    @Ignore
    public Restaurant(Integer idRestaurant, String name, String address) {
        this.idRestaurant = idRestaurant;
        this.name = name;
        this.address = address;
    }

    public Integer getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Integer idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "(id = " + idRestaurant + ") " + name + " - " + address;
    }


}
