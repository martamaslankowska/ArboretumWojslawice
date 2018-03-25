package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-25.
 */

public class Hotel {

    private int idHotel;
    private String name;
    private String address;
    private int phone;
    private String website;
    private double distance;
    private double rating;

    public Hotel(int idHotel, String name, String address, int phone, String website, double distance, double rating) {
        this.idHotel = idHotel;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.distance = distance;
        this.rating = rating;
    }

    public Hotel(int idHotel, String name, String address) {
        this.idHotel = idHotel;
        this.name = name;
        this.address = address;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "(id = " + idHotel + ") " + name + " - " + address;
    }

}
