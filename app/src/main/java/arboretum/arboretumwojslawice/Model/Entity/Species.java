package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-24.
 */

@Entity(tableName = "Species", foreignKeys =
        @ForeignKey(entity = Genus.class, parentColumns = "Name", childColumns = "GenusName"))
public class Species {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdSpecies")
    private int idSpecies;

    @ColumnInfo(name = "GenusName")
    @NonNull
    private String genusName;

    @ColumnInfo(name = "SpeciesName")
    @NonNull
    private String speciesName;


}