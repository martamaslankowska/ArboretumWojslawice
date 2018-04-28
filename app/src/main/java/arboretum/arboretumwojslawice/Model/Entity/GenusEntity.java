package arboretum.arboretumwojslawice.Model.Entity;

/**
 * Created by Komputer on 2018-03-24.
 */


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import android.support.annotation.NonNull;

@Entity(tableName = "Genus")
//        indices = {@Index(value = {"IdGenus"}, unique = true)})
public class GenusEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Name")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
