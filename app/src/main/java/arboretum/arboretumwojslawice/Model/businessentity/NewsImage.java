package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;
import android.content.Context;


public class NewsImage {

    @ColumnInfo(name = "IdNewsImage")
    private Integer idNewsImage;

    @ColumnInfo(name = "IdNews")
    private Integer idNews;

    @ColumnInfo(name = "ExtraImage")
    private String extraImage;


    public NewsImage(Integer idNewsImage, Integer idNews, String extraImage) {
        this.idNewsImage = idNewsImage;
        this.idNews = idNews;
        this.extraImage = extraImage;
    }


    public Integer getIdNewsImage() {
        return idNewsImage;
    }

    public void setIdNewsImage(Integer idNewsImage) {
        this.idNewsImage = idNewsImage;
    }

    public Integer getIdNews() {
        return idNews;
    }

    public void setIdNews(Integer idNews) {
        this.idNews = idNews;
    }

    public String getExtraImage() {
        return extraImage;
    }

    public void setExtraImage(String extraImage) {
        this.extraImage = extraImage;
    }

    public int getImageId(Context c) {
        return c.getResources().getIdentifier("arboretum.arboretumwojslawice:drawable/" + extraImage, null, null);

    }
}
