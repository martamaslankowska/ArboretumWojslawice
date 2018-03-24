package arboretum.arboretumwojslawice.Model.Entity;

/**
 * Created by Komputer on 2018-03-24.
 */


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "Genus")
public class Genus {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Name")
    private int name;

}
