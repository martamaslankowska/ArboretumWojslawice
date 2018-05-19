package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import android.content.Context;

import java.util.Date;
import java.util.List;

import arboretum.arboretumwojslawice.Commons.AdapterItem;
import arboretum.arboretumwojslawice.R;

/**
 * Created by Komputer on 2018-03-25.
 */

public class Plant implements AdapterItem {

    private static final String[] DEFAULT_ICONS_NAMES = {"ic_tree_deciduous_black", "ic_tree_conifer_black", "ic_clover_black", "ic_world_black"};
    private static final String[] DEFAULT_IMAGES_NAMES = {"plant_default_00", "plant_default_01", "plant_default_02", "plant_default_03"};

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

    @ColumnInfo(name = "IdKind")
    private Integer kind;

    @ColumnInfo(name = "Image")
    private String image;

    @ColumnInfo(name = "SeasonBegin")
    private Integer seasonBegin;

    @ColumnInfo(name = "SeasonEnd")
    private Integer seasonEnd;

    @ColumnInfo(name = "Description")
    private String description;

    @ColumnInfo(name = "Favourite")
    private Boolean favourite;

    @Ignore
    private List<Location> locations;


    @Ignore
    public Plant() {}

    @Ignore
    public Plant(Integer idPlant, String genusName, String speciesName, String latinName, String name, Integer kind, String image, Integer seasonBegin, Integer seasonEnd, String description, List<Location> locations) {
        this.idPlant = idPlant;
        this.genusName = genusName;
        this.speciesName = speciesName;
        this.latinName = latinName;
        this.name = name;
        this.kind = kind;
        this.image = image;
        this.seasonBegin = seasonBegin;
        this.seasonEnd = seasonEnd;
        this.description = description;
        this.locations = locations;
    }

    // needed for Dao to get all plants
    public Plant(Integer idPlant, String genusName, String speciesName, String latinName, String name, Integer kind, String image, Integer seasonBegin, Integer seasonEnd, String description, Boolean favourite) {
        this.idPlant = idPlant;
        this.genusName = genusName;
        this.speciesName = speciesName;
        this.latinName = latinName;
        this.name = name;
        this.kind = kind;
        this.image = image;
        this.seasonBegin = seasonBegin;
        this.seasonEnd = seasonEnd;
        this.description = description;
        this.favourite = favourite;
    }

    @Ignore
    public Plant(List<Location> locations) {
        this.locations = locations;
    }

    @Ignore
    public Plant(Integer idPlant, String genusName, Integer kind) {
        this.idPlant = idPlant;
        this.genusName = genusName;
        this.kind = kind;
    }

    @Ignore
    public Plant(String name, Integer idPlant, String genusName, Integer kind, String image) {
        this.name = name;
        this.idPlant = idPlant;
        this.genusName = genusName;
        this.kind = kind;
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

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getImageId(Context c) {
        if (image == null || image.isEmpty())
            image = Plant.DEFAULT_ICONS_NAMES[kind];
        return c.getResources().getIdentifier("arboretum.arboretumwojslawice:drawable/" + image, null, null);
    }

    public int getImageIdBig(Context c) {
        String bigImage = image;
        if (image == null || image.isEmpty() || image.equals(Plant.DEFAULT_ICONS_NAMES[kind]))
            bigImage = Plant.DEFAULT_IMAGES_NAMES[kind];
        return c.getResources().getIdentifier("arboretum.arboretumwojslawice:drawable/" + bigImage, null, null);
    }

    public String getImageIdString(Context c) {
        return String.valueOf(getImageId(c));
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

    public String getSeasonBeginDateString() {
        String sDate = String.valueOf(getSeasonBegin());
        if (sDate.length() != 8)
            return "";
        return sDate.substring(6, 8) + "." + sDate.substring(4, 6);
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

    public String getSeasonEndDateString() {
        String sDate = String.valueOf(getSeasonEnd());
        if (sDate.length() != 8)
            return "";
        return sDate.substring(6, 8) + "." + sDate.substring(4, 6);
    }

    public String getSeasonStartAndEndDateString(Context context) {
        if (getSeasonBeginDateString().equals("") || getSeasonEndDateString().equals(""))
            return context.getResources().getString(R.string.plant_seasson_date);
        return getSeasonBeginDateString() + " - " + getSeasonEndDateString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "(id = " + idPlant + ") name: " + name + " (genus: " + genusName + ") --> " + kind;
    }

    @Override
    public int getItemType() {
        return 0;
    }

}
