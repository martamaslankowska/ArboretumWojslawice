package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import io.reactivex.annotations.NonNull;

import java.time.LocalDate;


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
        @ForeignKey(entity = Kind.class, parentColumns = "IdKind", childColumns = "IdKind"),
        @ForeignKey(entity = Species.class, parentColumns = "IdSpecies", childColumns = "IdSpecies")})
public class Plant {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdPlant")
    private int idPlant;

    @ColumnInfo(name = "Name")
    private String name;

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


    public int getIdPlant() {
        return idPlant;
    }

    public void setIdPlant(int idPlant) {
        this.idPlant = idPlant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
