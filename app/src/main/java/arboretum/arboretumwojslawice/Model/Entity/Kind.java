package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-23.
 */

@Entity(tableName = "Kinds")
public class Kind {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdKind")
    private int idKind;

    @ColumnInfo(name = "Name")
    @NonNull
    private String name;


    public int getIdKind() {
        return idKind;
    }

    public void setIdKind(int idKind) {
        this.idKind = idKind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
