package arboretum.arboretumwojslawice.Commons.map;

/**
 * Created by Basia on 2018-05-01.
 */

public class LonLat {
    public double lon, lat;

    public LonLat(double lon, double lat){
        this.lon=lon;
        this.lat = lat;
    }

    @Override
    public String toString(){
        return "(" + Double.toString(lon) + "," + Double.toString(lat) + ")";
    }
}
