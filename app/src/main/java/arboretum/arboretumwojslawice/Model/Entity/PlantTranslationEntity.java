package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-24.
 */

@Entity(tableName = "PlantsTranslations",
        foreignKeys = @ForeignKey(entity = PlantEntity.class, parentColumns = "IdPlant", childColumns = "IdPlant"))
public class PlantTranslationEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "TranslationCode")
    private String translationCode;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "IdPlant")
    private int idPlant;

    @ColumnInfo(name = "Description")
    private String description;


    public String getTranslationCode() {
        return translationCode;
    }

    public void setTranslationCode(String translationCode) {
        this.translationCode = translationCode;
    }

    public int getIdPlant() {
        return idPlant;
    }

    public void setIdPlant(int idPlant) {
        this.idPlant = idPlant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}