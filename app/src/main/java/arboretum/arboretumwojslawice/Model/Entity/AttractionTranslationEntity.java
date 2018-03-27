package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import android.support.annotation.NonNull;

/**
 * Created by Komputer on 2018-03-24.
 */

@Entity(tableName = "AttractionsTranslations",
        foreignKeys = @ForeignKey(entity = AttractionEntity.class, parentColumns = "IdAttraction", childColumns = "IdAttraction"),
        primaryKeys = {"TranslationCode", "IdAttraction"})
public class AttractionTranslationEntity {

    @NonNull
    @ColumnInfo(name = "TranslationCode")
    private String translationCode;

    @NonNull
    @ColumnInfo(name = "IdAttraction")
    private int idAttraction;

    @NonNull
    @ColumnInfo(name = "Name")
    private String name;

    @NonNull
    @ColumnInfo(name = "Description")
    private String description;


//    public AttractionTranslationEntity(String translationCode, int idAttraction, String name, String description) {
//        this.translationCode = translationCode;
//        this.idAttraction = idAttraction;
//        this.name = name;
//        this.description = description;
//    }


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