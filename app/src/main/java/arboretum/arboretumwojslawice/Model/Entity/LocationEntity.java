package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-23.
 */

@Entity(tableName = "Locations",
        foreignKeys = {
            @ForeignKey(entity = PlantEntity.class, parentColumns = "IdPlant", childColumns = "IdPlant")})
public class LocationEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdLocation")
    private int idLocation;

    @ColumnInfo(name = "IdPlant")
    private int idPlant;

    @ColumnInfo(name = "X")
    @NonNull
    private double x;

    @ColumnInfo(name = "Y")
    @NonNull
    private double y;


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

    public int getIdPlant() {
        return idPlant;
    }

    public void setIdPlant(int idPlant) {
        this.idPlant = idPlant;
    }
}
