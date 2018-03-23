package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-23.
 */

@Entity(tableName = "Coordinates",
        foreignKeys = {
            @ForeignKey(entity = Plant.class, parentColumns = "IdPlant", childColumns = "IdPlant")})
public class Coordinate {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdCoordinate")
    private int idCoordinate;

    @ColumnInfo(name = "X")
    @NonNull
    private double x;

    @ColumnInfo(name = "Y")
    @NonNull
    private double y;

    @ColumnInfo(name = "IdPlant")
    private int idPlant;


    public int getIdCoordinate() {
        return idCoordinate;
    }

    public void setIdCoordinate(int idCoordinate) {
        this.idCoordinate = idCoordinate;
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

    public int getIdPlant() {
        return idPlant;
    }

    public void setIdPlant(int idPlant) {
        this.idPlant = idPlant;
    }
}
