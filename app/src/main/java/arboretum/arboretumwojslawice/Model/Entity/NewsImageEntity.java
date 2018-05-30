package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "NewsImages",
        foreignKeys = @ForeignKey(entity = NewsEntity.class, parentColumns = "IdNews", childColumns = "IdNews"))
public class NewsImageEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdNewsImage")
    private int idNewsImage;

    @NonNull
    @ColumnInfo(name = "IdNews")
    private int idNews;

    @NonNull
    @ColumnInfo(name = "ExtraImage")
    private String extraImage;


    public int getIdNewsImage() {
        return idNewsImage;
    }

    public void setIdNewsImage(int idNewsImage) {
        this.idNewsImage = idNewsImage;
    }

    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    public String getExtraImage() {
        return extraImage;
    }

    public void setExtraImage(String extraImage) {
        this.extraImage = extraImage;
    }
}
