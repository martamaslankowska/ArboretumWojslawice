package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-23.
 */

@Entity(tableName = "FavouritePlants",
        foreignKeys = @ForeignKey(entity = Plant.class, parentColumns = "IdPlant", childColumns = "IdPlant"))
public class FavouritePlant {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "IdPlant")
    private int idPlant;


    public int getIdPlant() {
        return idPlant;
    }

    public void setIdPlant(int idPlant) {
        this.idPlant = idPlant;
    }
}