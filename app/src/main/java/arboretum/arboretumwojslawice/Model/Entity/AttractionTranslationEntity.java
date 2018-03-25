package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-24.
 */

@Entity(tableName = "AttractionsTranslations",
        foreignKeys = @ForeignKey(entity = AttractionEntity.class, parentColumns = "IdAttraction", childColumns = "IdAttraction"))
public class AttractionTranslationEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "TranslationCode")
    private String translationCode;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "IdAttraction")
    private int idAttraction;

    @ColumnInfo(name = "Name")
    @NonNull
    private String name;

    @ColumnInfo(name = "Description")
    @NonNull
    private String description;


    public String getTranslationCode() {
        return translationCode;
    }

    public void setTranslationCode(String translationCode) {
        this.translationCode = translationCode;
    }

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
}