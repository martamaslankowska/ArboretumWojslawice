package arboretum.arboretumwojslawice.Model.businessentity;

/**
 * Created by Komputer on 2018-03-25.
 */

public class Restaurant {

    private int idRestaurant;
    private String name;
    private String address;
    private int phone;
    private String website;
    private double distance;
    private double rating;

    public Restaurant(int idRestaurant, String name, String address, int phone, String website, double distance, double rating) {
        this.idRestaurant = idRestaurant;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.distance = distance;
        this.rating = rating;
    }

    public Restaurant(int idRestaurant, String name, String address) {
        this.idRestaurant = idRestaurant;
        this.name = name;
        this.address = address;
    }

    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
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
        return "(id = " + idRestaurant + ") " + name + " - " + address;
    }


}
