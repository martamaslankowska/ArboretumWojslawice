package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-24.
 */


@Entity(tableName = "Attractions")
public class Attraction {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdAttraction")
    private int idAttraction;

    @ColumnInfo(name = "Name")
    @NonNull
    private String name;

    @ColumnInfo(name = "Description")
    @NonNull
    private String description;

    @ColumnInfo(name = "Image")
    private int image;


    public int getIdAttraction() {
        return idAttraction;
    }

    public void setIdAttraction(int idAttraction) {
        this.idAttraction = idAttraction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
