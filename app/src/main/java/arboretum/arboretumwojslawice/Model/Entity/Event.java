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

    @ColumnInfo(name = "DateBegin")
    @NonNull
    private int dateBegin;

    @ColumnInfo(name = "DateEnd")
    private int dateEnd;

    @ColumnInfo(name = "TimeBegin")
    private int timeBegin;

    @ColumnInfo(name = "TimeEnd")
    private int timeEnd;


    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(int dateBegin) {
        this.dateBegin = dateBegin;
    }

    public int getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(int dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(int timeBegin) {
        this.timeBegin = timeBegin;
    }

    public int getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(int timeEnd) {
        this.timeEnd = timeEnd;
    }

}