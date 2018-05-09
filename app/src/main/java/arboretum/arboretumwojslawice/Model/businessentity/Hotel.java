package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;

import arboretum.arboretumwojslawice.Commons.AdapterItem;
import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-25.
 */


public class Hotel implements AdapterItem {

    @ColumnInfo(name = "IdHotel")
    private Integer idHotel;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Address")
    private String address;

    @ColumnInfo(name = "Phone")
    private Integer phone;

    @ColumnInfo(name = "Website")
    private String website;

    @ColumnInfo(name = "Distance")
    private Double distance;

    @ColumnInfo(name = "Rating")
    private Double rating;

    @ColumnInfo(name = "Image")
    private String image;


    public Hotel(Integer idHotel, String name, String address, Integer phone, String website, Double distance, Double rating, String image) {
        this.idHotel = idHotel;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.distance = distance;
        this.rating = rating;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getImageId(Context c) {
        return c.getResources().getIdentifier("arboretum.arboretumwojslawice:drawable/" + image, null, null);
    }

    public String getImageIdString(Context c) {
        return String.valueOf(getImageId(c));
    }

    @Override
    public String toString() {
        return "(id = " + idHotel + ") " + name + " - " + address;
    }

    public String getPhoneString() {
        return phone.toString();
    }

    public String getDistanceString() {
        return distance.toString() + " km";
    }

    public String getRatingString() {
        return rating.toString();
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
