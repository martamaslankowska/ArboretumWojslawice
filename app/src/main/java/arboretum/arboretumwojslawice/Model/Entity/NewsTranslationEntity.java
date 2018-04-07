package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "NewsTranslations",
        foreignKeys = @ForeignKey(entity = NewsEntity.class, parentColumns = "IdNews", childColumns = "IdNews"),
        primaryKeys = {"TranslationCode", "IdNews"})
//        indices = {@Index(value = {"TranslationCode", "IdNews"}, unique = true)})
public class NewsTranslationEntity {

    @NonNull
    @ColumnInfo(name = "TranslationCode")
    private String translationCode;

    @NonNull
    @ColumnInfo(name = "IdNews")
    private int idNews;

    @ColumnInfo(name = "Name")
    @NonNull
    private String name;

    @ColumnInfo(name = "Description")
    private String description;


    @NonNull
    public String getTranslationCode() {
        return translationCode;
    }

    public void setTranslationCode(@NonNull String translationCode) {
        this.translationCode = translationCode;
    }

    @NonNull
    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(@NonNull int idNews) {
        this.idNews = idNews;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}