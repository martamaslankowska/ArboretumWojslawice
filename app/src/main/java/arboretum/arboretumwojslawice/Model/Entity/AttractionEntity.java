package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-24.
 */


@Entity(tableName = "Attractions")
public class AttractionEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdAttraction")
    private int idAttraction;

    @ColumnInfo(name = "Image")
    private int image;


    public AttractionEntity(int idAttraction, int image) {
        this.idAttraction = idAttraction;
        this.image = image;
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
}
