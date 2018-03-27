package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Time;
import java.util.Date;

import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-25.
 */

public class Event {

    private int idEvent;
    private String type;
    private String name;
    private int dateBegin;
    private int dateEnd;
    private int timeBegin;
    private int timeEnd;
    private String descritpion;


    public Event(int idEvent, String type, String name, int dateBegin, int dateEnd, int timeBegin, int timeEnd, String descritpion) {
        this.idEvent = idEvent;
        this.type = type;
        this.name = name;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.descritpion = descritpion;
    }

    public Event(int idEvent, String type, int dateBegin) {
        this.idEvent = idEvent;
        this.type = type;
        this.dateBegin = dateBegin;
    }

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

    public int getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(int dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateBeginDate() {
        int day = dateBegin - ((dateBegin/100)*100);
        int month = (dateBegin/100) - ((dateBegin/10000)*100);
        int year = dateBegin/10000;
        Date date = new Date(year, month, day);
        return date;
    }

    public void setDateBegin(Date dateBegin) {
        int day = dateBegin.getDay();
        int month = dateBegin.getMonth();
        int year = dateBegin.getYear();
        int intDate = 10000*year + 100*month + day;
        this.dateBegin = intDate;
    }

    public int getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(int dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getDateEndDate() {
        int day = dateEnd - ((dateEnd/100)*100);
        int month = (dateEnd/100) - ((dateEnd/10000)*100);
        int year = dateEnd/10000;
        Date date = new Date(year, month, day);
        return date;
    }

    public void setDateEnd(Date dateEnd) {
        int day = dateEnd.getDay();
        int month = dateEnd.getMonth();
        int year = dateEnd.getYear();
        int intDate = 10000*year + 100*month + day;
        this.dateEnd = intDate;
    }

    public int getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(int timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Date getTimeBeginDate() {
        int minutes = timeBegin - ((timeBegin/100)*100);
        int hour = timeBegin/100;
        Date time = new Date();
        time.setHours(hour);
        time.setMinutes(minutes);
        return time;
    }

    public void setTimeBegin(Date time) {
        int minutes = time.getMinutes();
        int hour = time.getHours();
        int intTime = hour*100 + minutes;
        this.timeBegin = intTime;
    }

    public int getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(int timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Date getTimeEndDate() {
        int minutes = timeEnd - ((timeEnd/100)*100);
        int hour = timeEnd/100;
        Date time = new Date();
        time.setHours(hour);
        time.setMinutes(minutes);
        return time;
    }

    public void setTimeEnd(Date time) {
        int minutes = time.getMinutes();
        int hour = time.getHours();
        int intTime = hour*100 + minutes;
        this.timeBegin = intTime;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }


    @Override
    public String toString() {
        return "(id = " + idEvent + ") " + type + " " + name + " - " + getDateBeginDate().getHours() + ":" + getDateBeginDate().getMinutes();
    }

}
