package arboretum.arboretumwojslawice.Model.businessentity;

import android.app.Application;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import android.content.Context;

import arboretum.arboretumwojslawice.Commons.AdapterItem;
import dagger.android.DaggerApplication;

public class News implements AdapterItem {

    @ColumnInfo(name = "IdNews")
    private Integer idNews;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Description")
    private String description;

    @ColumnInfo(name = "Image")
    private String image;

    @ColumnInfo(name = "Date")
    private Integer date;


    public News(Integer idNews, String name, String description, String image, Integer date) {
        this.idNews = idNews;
        this.name = name;
        this.description = description;
        this.image = image;
        this.date = date;
    }

    @Ignore
    public News(Integer idNews, String name)
    {
        this.idNews = idNews;
        this.name = name;
    }

    @Ignore
    public News()
    {

    }

    public Integer getIdNews() {
        return idNews;
    }

    public String getIdString() {return String.valueOf(idNews); }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getImageId(Context c) {
        return c.getResources().getIdentifier("arboretum.arboretumwojslawice:drawable/" + image, null, null);
    }

    public String getImageIdString(Context c) {
        return String.valueOf(getImageId(c));
    }

    public Integer getDate() {
        return date;
    }

    public String getDateString() {
        String sDate = String.valueOf(date);
        return sDate.substring(6,8)+"/"+sDate.substring(4,6)+"/"+sDate.substring(0,4);
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "(id = " + idNews + ") " + name;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
