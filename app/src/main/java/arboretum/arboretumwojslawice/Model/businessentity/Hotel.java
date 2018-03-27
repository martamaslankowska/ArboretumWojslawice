package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-25.
 */


public class Hotel {

    private Integer idHotel;
    private String name;
    private String address;
    private Integer phone;
    private String website;
    private Double distance;
    private Double rating;

    public Hotel(Integer idHotel, String name, String address, Integer phone, String website, Double distance, Double rating) {
        this.idHotel = idHotel;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.distance = distance;
        this.rating = rating;
    }

    @Ignore
    public Hotel(Integer idHotel, String name, String address) {
        this.idHotel = idHotel;
        this.name = name;
        this.address = address;
    }

    public Integer getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Integer idHotel) {
        this.idHotel = idHotel;
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
        return "(id = " + idHotel + ") " + name + " - " + address;
    }

}
