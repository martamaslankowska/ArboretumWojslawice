package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-25.
 */

public class Attraction {

    @ColumnInfo(name = "IdAttraction")
    private Integer idAttraction;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Description")
    private String description;

    @ColumnInfo(name = "Image")
    private Integer image;


    public Attraction(Integer idAttraction, String name, String description, Integer image) {
        this.idAttraction = idAttraction;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Integer getIdAttraction() {
        return idAttraction;
    }

    public void setIdAttraction(Integer idAttraction) {
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

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "(id = " + idAttraction + ") " + name + " --> " + description.substring(0, 20) + "...";
    }
}
