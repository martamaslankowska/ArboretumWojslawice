package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

/**
 * Created by Komputer on 2018-03-24.
 */

@Entity(tableName = "KindsTranslations",
        foreignKeys = @ForeignKey(entity = KindEntity.class, parentColumns = "IdKind", childColumns = "IdKind"),
        primaryKeys = {"TranslationCode", "IdKind"})
public class KindTranslationEntity {

    @NonNull
    @ColumnInfo(name = "TranslationCode")
    private String translationCode;

    @NonNull
    @ColumnInfo(name = "IdKind")
    private int idKind;

    @ColumnInfo(name = "Name")
    @NonNull
    private String name;


    public String getTranslationCode() {
        return translationCode;
    }

    public void setTranslationCode(String translationCode) {
        this.translationCode = translationCode;
    }

    public int getIdKind() {
        return idKind;
    }

    public void setIdKind(int idKind) {
        this.idKind = idKind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
