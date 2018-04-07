package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;

import arboretum.arboretumwojslawice.Commons.AdapterItem;

public class News {

    @ColumnInfo(name = "IdNews")
    private Integer idNews;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Description")
    private String description;

    @ColumnInfo(name = "Image")
    private Integer image;

    @ColumnInfo(name = "Date")
    private Integer date;


    public News(Integer idNews, String name, String description, Integer image, Integer date) {
        this.idNews = idNews;
        this.name = name;
        this.description = description;
        this.image = image;
        this.date = date;
    }


    public Integer getIdNews() {
        return idNews;
    }

    public void setIdNews(Integer idNews) {
        this.idNews = idNews;
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

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "(id = " + idNews + ") " + name;
    }


}
