package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

/**
 * Created by Komputer on 2018-03-24.
 */

@Entity(tableName = "PricesTranslations",
        foreignKeys = @ForeignKey(entity = PriceEntity.class, parentColumns = "IdPrice", childColumns = "IdPrice"),
        primaryKeys = {"TranslationCode", "IdPrice"})
//        indices = {@Index(value = {"TranslationCode", "IdPrice"}, unique = true)})
public class PriceTranslationEntity {

    @NonNull
    @ColumnInfo(name = "TranslationCode")
    private String translationCode;

    @NonNull
    @ColumnInfo(name = "IdPrice")
    private int idPrice;

    @ColumnInfo(name = "Type")
    @NonNull
    private String type;

    @ColumnInfo(name = "Description")
    private String description;


    public PriceTranslationEntity(@NonNull String translationCode, @NonNull int idPrice, @NonNull String type, String description) {
        this.translationCode = translationCode;
        this.idPrice = idPrice;
        this.type = type;
        this.description = description;
    }

    public String getTranslationCode() {
        return translationCode;
    }

    public void setTranslationCode(String translationCode) {
        this.translationCode = translationCode;
    }

    public int getIdPrice() {
        return idPrice;
    }

    public void setIdPrice(int idPrice) {
        this.idPrice = idPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}