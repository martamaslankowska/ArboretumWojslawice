package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-24.
 */


@Entity(tableName = "Attractions")
//        indices = {@Index(value = {"IdAttraction"}, unique = true)})
public class AttractionEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdAttraction")
    private int idAttraction;

    @ColumnInfo(name = "Image")
    private int image;

    @ColumnInfo(name = "Distance")
    private double distance;


    public AttractionEntity(int idAttraction, int image, double distance) {
        this.idAttraction = idAttraction;
        this.image = image;
        this.distance = distance;
    }

    public int getIdAttraction() {
        return idAttraction;
    }

    public void setIdAttraction(int idAttraction) {
        this.idAttraction = idAttraction;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
