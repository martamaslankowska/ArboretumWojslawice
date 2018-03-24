package arboretum.arboretumwojslawice.Model.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.time.LocalDate;
import java.time.LocalTime;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-24.
 */

@Entity(tableName = "Events")
public class Event {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "IdEvent")
    private int idEvent;

    @ColumnInfo(name = "Type")
    private String type;

    @ColumnInfo(name = "Name")
    @NonNull
    private String name;

    @ColumnInfo(name = "DateBegin")
    @NonNull
    private LocalDate dateBegin;

    @ColumnInfo(name = "DateEnd")
    private LocalDate dateEnd;

    @ColumnInfo(name = "TimeBegin")
    private LocalTime timeBegin;

    @ColumnInfo(name = "TimeEnd")
    private LocalTime timeEnd;

    @ColumnInfo(name = "Description")
    private String descritpion;


    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public LocalTime getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(LocalTime timeBegin) {
        this.timeBegin = timeBegin;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }
}