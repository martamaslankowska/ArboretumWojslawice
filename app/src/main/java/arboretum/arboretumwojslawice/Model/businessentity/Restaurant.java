package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import android.content.Context;

import arboretum.arboretumwojslawice.Commons.AdapterItem;

/**
 * Created by Komputer on 2018-03-25.
 */

public class Restaurant implements AdapterItem {

    @ColumnInfo(name = "IdRestaurant")
    private Integer idRestaurant;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Address")
    private String address;

    @ColumnInfo(name = "Phone")
    private String phone;

    @ColumnInfo(name = "Website")
    private String website;

    @ColumnInfo(name = "Distance")
    private Double distance;

    @ColumnInfo(name = "Rating")
    private Double rating;

    @ColumnInfo(name = "Image")
    private String image;


    public Restaurant(Integer idRestaurant, String name, String address, String phone, String website, Double distance, Double rating, String image) {
        this.idRestaurant = idRestaurant;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.distance = distance;
        this.rating = rating;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getImageId(Context c) {
        return c.getResources().getIdentifier("arboretum.arboretumwojslawice:drawable/" + image, null, null);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getImageIdString(Context c) {
        return String.valueOf(getImageId(c));
    }

    @Override
    public String toString() {
        return "(id = " + idRestaurant + ") " + name + " - " + address;
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
