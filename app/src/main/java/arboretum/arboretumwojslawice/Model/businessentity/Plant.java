package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;

import java.util.Date;
import java.util.List;

import arboretum.arboretumwojslawice.Commons.AdapterItem;

/**
 * Created by Komputer on 2018-03-25.
 */

public class Plant implements AdapterItem {

    @ColumnInfo(name = "IdPlant")
    private Integer idPlant;

    @ColumnInfo(name = "GenusName")
    private String genusName;

    @ColumnInfo(name = "SpeciesName")
    private String speciesName;

    @ColumnInfo(name = "LatinName")
    private String latinName;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "KindsTranslations.Name")
    private String kindName;

    @ColumnInfo(name = "Image")
    private Integer image;

    @ColumnInfo(name = "SeasonBegin")
    private Integer seasonBegin;

    @ColumnInfo(name = "SeasonEnd")
    private Integer seasonEnd;

    @ColumnInfo(name = "Description")
    private String description;

    @Ignore
    private List<Location> locations;


    @Ignore
    public Plant() {
    }  // default constructor for FavouritePlant to handle

    @Ignore
    public Plant(Integer idPlant, String genusName, String speciesName, String latinName, String name, String kindName, Integer image, Integer seasonBegin, Integer seasonEnd, String description, List<Location> locations) {
        this.idPlant = idPlant;
        this.genusName = genusName;
        this.speciesName = speciesName;
        this.latinName = latinName;
        this.name = name;
        this.kindName = kindName;
        this.image = image;
        this.seasonBegin = seasonBegin;
        this.seasonEnd = seasonEnd;
        this.description = description;
        this.locations = locations;
    }

    // needed for Dao to get all plants
    public Plant(Integer idPlant, String genusName, String speciesName, String latinName, String name, String kindName, Integer image, Integer seasonBegin, Integer seasonEnd, String description) {
        this.idPlant = idPlant;
        this.genusName = genusName;
        this.speciesName = speciesName;
        this.latinName = latinName;
        this.name = name;
        this.kindName = kindName;
        this.image = image;
        this.seasonBegin = seasonBegin;
        this.seasonEnd = seasonEnd;
        this.description = description;
    }

    @Ignore
    public Plant(List<Location> locations) {
        this.locations = locations;
    }

    @Ignore
    public Plant(Integer idPlant, String genusName, String kindName) {
        this.idPlant = idPlant;
        this.genusName = genusName;
        this.kindName = kindName;
    }

    @Ignore
    public Plant(Integer idPlant, String genusName, String kindName, Integer image) {
        this.idPlant = idPlant;
        this.genusName = genusName;
        this.kindName = kindName;
        this.image = image;
    }

    public Integer getIdPlant() {
        return idPlant;
    }

    public void setIdPlant(Integer idPlant) {
        this.idPlant = idPlant;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenusName() {
        return genusName;
    }

    public void setGenusName(String genusName) {
        this.genusName = genusName;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public Integer getSeasonBegin() {
        return seasonBegin;
    }

    public void setSeasonBegin(Integer seasonBegin) {
        this.seasonBegin = seasonBegin;
    }

    public Date getSeasonBeginDate() {
        Integer day = seasonBegin - ((seasonBegin / 100) * 100);
        Integer month = (seasonBegin / 100) - ((seasonBegin / 10000) * 100);
        Integer year = seasonBegin / 10000;
        Date date = new Date(year, month, day);
        return date;
    }

    public void setSeasonBegin(Date seasonBegin) {
        Integer day = seasonBegin.getDay();
        Integer month = seasonBegin.getMonth();
        Integer year = seasonBegin.getYear();
        Integer intDate = 10000 * year + 100 * month + day;
        this.seasonBegin = intDate;
    }

    public Integer getSeasonEnd() {
        return seasonEnd;
    }

    public void setSeasonEnd(Integer seasonEnd) {
        this.seasonEnd = seasonEnd;
    }

    public Date getSeasonEndDate() {
        Integer day = seasonEnd - ((seasonEnd / 100) * 100);
        Integer month = (seasonEnd / 100) - ((seasonEnd / 10000) * 100);
        Integer year = seasonEnd / 10000;
        Date date = new Date(year, month, day);
        return date;
    }

    public void setSeasonEnd(Date seasonEnd) {
        Integer day = seasonEnd.getDay();
        Integer month = seasonEnd.getMonth();
        Integer year = seasonEnd.getYear();
        Integer intDate = 10000 * year + 100 * month + day;
        this.seasonEnd = intDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "(id = " + idPlant + ") name: " + name + " (genus: " + genusName + ") --> " + kindName;
    }

    @Override
    public int getItemType() {
        return 0;
    }
}
