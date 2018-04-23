package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import io.reactivex.annotations.NonNull;


/*
IMPORTANT - Room must have only one constructor to deal with
That's why it's good to use @Ignore on other constructors to tell Room not to bother
*/

/*
- It's possible to add primary keys in a different way: @Entity(primaryKeys = {"firstName", "lastName"})
- Also pair of unique values can be provided: @Entity(indices = {@Index(value = {"first_name", "last_name"}, unique = true)})
- Having in our class 'user_id' (actually userID with column name changed), we can add foreign key (define relationship between objects):
  @Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id"))
- It is also possible to create nested objects (see more info: https://developer.android.com/training/data-storage/room/defining-data.html)
*/

@Entity(tableName = "Plants", foreignKeys = {
        @ForeignKey(entity = KindEntity.class, parentColumns = "IdKind", childColumns = "IdKind"),
        @ForeignKey(entity = SpeciesEntity.class, parentColumns = "IdSpecies", childColumns = "IdSpecies")})
//        indices = {@Index(value = {"IdPlant"}, unique = true)})
public class PlantEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdPlant")
    private int idPlant;

    @ColumnInfo(name = "LatinName")
    private String latinName;

    @ColumnInfo(name = "IdSpecies")
    private int idSpecies;

    @ColumnInfo(name = "IdKind")
    @NonNull
    private int idKind;

    @ColumnInfo(name = "Image")
    private int image;

    @ColumnInfo(name = "SeasonBegin")
    private int seasonBegin;

    @ColumnInfo(name = "SeasonEnd")
    private int seasonEnd;

    @ColumnInfo(name = "Favourite")
    private boolean favourite;


    public int getIdPlant() {
        return idPlant;
    }

    public void setIdPlant(int idPlant) {
        this.idPlant = idPlant;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public int getIdSpecies() {
        return idSpecies;
    }

    public void setIdSpecies(int idSpecies) {
        this.idSpecies = idSpecies;
    }

    public int getIdKind() {
        return idKind;
    }

    public void setIdKind(int idKind) {
        this.idKind = idKind;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getSeasonBegin() {
        return seasonBegin;
    }

    public void setSeasonBegin(int seasonBegin) {
        this.seasonBegin = seasonBegin;
    }

    public int getSeasonEnd() {
        return seasonEnd;
    }

    public void setSeasonEnd(int seasonEnd) {
        this.seasonEnd = seasonEnd;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
