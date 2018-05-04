package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import arboretum.arboretumwojslawice.Commons.AdapterItem;

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

    public String getFullDate() {
        String sDate = String.valueOf(date);
        String month = "error";

        String language = Locale.getDefault().getLanguage();
        if(language == "pl")
        {
            Log.i("kod pl: ", sDate.substring(4,6));
            switch(sDate.substring(4,6))
            {
                case "01": month = "stycznia"; break;
                case "02": month = "lutego"; break;
                case "03": month = "marca"; break;
                case "04": month = "kwietnia"; break;
                case "05": month = "maja"; break;
                case "06": month = "czerwca"; break;
                case "07": month = "lipca"; break;
                case "08": month = "sierpnia"; break;
                case "09": month = "września"; break;
                case "10": month = "października"; break;
                case "11": month = "listopada"; break;
                case "12": month = "grudnia"; break;
                default: month = "zły miesiąc";
            }
        }
        else if(language == "en")
        {
            switch(sDate.substring(4,6))
            {
                case "01": month = "January"; break;
                case "02": month = "February"; break;
                case "03": month = "March"; break;
                case "04": month = "April"; break;
                case "05": month = "May"; break;
                case "06": month = "June"; break;
                case "07": month = "July"; break;
                case "08": month = "August"; break;
                case "09": month = "Semptember"; break;
                case "10": month = "October"; break;
                case "11": month = "November"; break;
                case "12": month = "December"; break;
                default: month = "bad month";
            }
        }
        else if(language == "de")
        {
            switch(sDate.substring(4,6))
            {
                case "01": month = "Januar"; break;
                case "02": month = "Februar"; break;
                case "03": month = "März"; break;
                case "04": month = "April"; break;
                case "05": month = "Mai"; break;
                case "06": month = "Juni"; break;
                case "07": month = "Juli"; break;
                case "08": month = "August"; break;
                case "09": month = "Semptember"; break;
                case "10": month = "Oktober"; break;
                case "11": month = "November"; break;
                case "12": month = "Dezember"; break;
                default: month = "schlechter Monat";
            }
        }



        return sDate.substring(6,8)+" "+month;
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
