package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Komputer on 2018-03-24.
 */

@Entity(tableName = "EventsTranslations",
        foreignKeys = @ForeignKey(entity = EventEntity.class, parentColumns = "IdEvent", childColumns = "IdEvent"),
        primaryKeys = {"TranslationCode", "IdEvent"})
public class EventTranslationEntity {

    @NonNull
    @ColumnInfo(name = "TranslationCode")
    private String translationCode;

    @NonNull
    @ColumnInfo(name = "IdEvent")
    private int idEvent;

    @ColumnInfo(name = "Type")
    private String type;

    @ColumnInfo(name = "Name")
    @NonNull
    private String name;

    @ColumnInfo(name = "Description")
    private String descritpion;


    public String getTranslationCode() {
        return translationCode;
    }

    public void setTranslationCode(String translationCode) {
        this.translationCode = translationCode;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }
}