package arboretum.arboretumwojslawice.Commons.map;

/**
 * Created by Basia on 2018-05-01.
 */

public class PixelCoordinates {
    public int x, y;

    public PixelCoordinates(int x, int y){
        this.x=x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "(" + Double.toString(x) + "," + Double.toString(y) + ")";
    }
}
