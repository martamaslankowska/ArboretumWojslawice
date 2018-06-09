package arboretum.arboretumwojslawice.Model.businessentity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;

import java.sql.Time;
import java.util.Date;

import arboretum.arboretumwojslawice.Commons.AdapterItem;
import arboretum.arboretumwojslawice.R;
import io.reactivex.annotations.NonNull;

/**
 * Created by Komputer on 2018-03-25.
 */

public class Event implements AdapterItem {

    @ColumnInfo(name = "IdEvent")
    private Integer idEvent;

    @ColumnInfo(name = "Type")
    private String type;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "DateBegin")
    private Integer dateBegin;

    @ColumnInfo(name = "DateEnd")
    private Integer dateEnd;

    @ColumnInfo(name = "TimeBegin")
    private Integer timeBegin;

    @ColumnInfo(name = "TimeEnd")
    private Integer timeEnd;

    @ColumnInfo(name = "Description")
    private String description;


    public Event(Integer idEvent, String type, String name, Integer dateBegin, Integer dateEnd, Integer timeBegin, Integer timeEnd, String description) {
        this.idEvent = idEvent;
        this.type = type;
        this.name = name;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.description = description;
    }

    @Ignore
    public Event(Integer idEvent, String type, Integer dateBegin) {
        this.idEvent = idEvent;
        this.type = type;
        this.dateBegin = dateBegin;
    }

    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
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

    public Integer getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Integer dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateBeginDate() {
        Integer day = dateBegin - ((dateBegin/100)*100);
        Integer month = (dateBegin/100) - ((dateBegin/10000)*100);
        Integer year = dateBegin/10000;
        Date date = new Date(year, month, day);
        return date;
    }

    public String getBeginDateString() {
        Integer day = dateBegin - ((dateBegin/100)*100);
        Integer month = (dateBegin/100) - ((dateBegin/10000)*100);
        Integer year = dateBegin/10000;

        return (day<10 ? "0" : "") + String.valueOf(day) + "." + (month<10 ? "0" : "") + String.valueOf(month);
    }



    public void setDateBegin(Date dateBegin) {
        Integer day = dateBegin.getDay();
        Integer month = dateBegin.getMonth();
        Integer year = dateBegin.getYear();
        Integer IntegerDate = 10000*year + 100*month + day;
        this.dateBegin = IntegerDate;
    }

    public Integer getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Integer dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getDateEndDate() {
        Integer day = dateEnd - ((dateEnd/100)*100);
        Integer month = (dateEnd/100) - ((dateEnd/10000)*100);
        Integer year = dateEnd/10000;
        Date date = new Date(year, month, day);
        return date;
    }

    public String getEndDateString() {
        Integer day = dateEnd - ((dateEnd/100)*100);
        Integer month = (dateEnd/100) - ((dateEnd/10000)*100);
        Integer year = dateEnd/10000;

        return (day<10 ? "0" : "") + String.valueOf(day) + "." + (month<10 ? "0" : "") + String.valueOf(month);
    }

    public void setDateEnd(Date dateEnd) {
        Integer day = dateEnd.getDay();
        Integer month = dateEnd.getMonth();
        Integer year = dateEnd.getYear();
        Integer IntegerDate = 10000*year + 100*month + day;
        this.dateEnd = IntegerDate;
    }

    public Integer getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Integer timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Date getTimeBeginDate() {
        Integer minutes = timeBegin - ((timeBegin/100)*100);
        Integer hour = timeBegin/100;
        Date time = new Date();
        time.setHours(hour);
        time.setMinutes(minutes);
        return time;
    }

    public String getTimeBeginString() {
        Integer minutes = timeBegin - ((timeBegin/100)*100);
        Integer hour = timeBegin/100;
        return hour + ":" + (minutes == 0 ? "00" : minutes);
    }

    public void setTimeBegin(Date time) {
        Integer minutes = time.getMinutes();
        Integer hour = time.getHours();
        Integer intTime = hour*100 + minutes;
        this.timeBegin = intTime;
    }

    public Integer getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Integer timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Date getTimeEndDate() {
        Integer minutes = timeEnd - ((timeEnd/100)*100);
        Integer hour = timeEnd/100;
        Date time = new Date();
        time.setHours(hour);
        time.setMinutes(minutes);
        return time;
    }

    public String getTimeEndString() {
        Integer minutes = timeEnd - ((timeEnd/100)*100);
        Integer hour = timeEnd/100;
        return hour + ":" + (minutes == 0 ? "00" : minutes);
    }

    public void setTimeEnd(Date time) {
        Integer minutes = time.getMinutes();
        Integer hour = time.getHours();
        Integer intTime = hour*100 + minutes;
        this.timeBegin = intTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "(id = " + idEvent + ") " + type + " " + name + " - " + getDateBeginDate().getHours() + ":" + getDateBeginDate().getMinutes();
    }

    public String getBeginDate() {
        return String.valueOf(getDateBeginDate().getYear()) + '-' + String.valueOf(getDateBeginDate().getMonth())
                + '-' + String.valueOf(getDateBeginDate().getDay());
    }

    @Override
    public int getItemType() {
        return 0;
    }




    public String getEventMonthInflectedString(Context context, Integer date) {
        Integer month = (date/100) - ((date/10000)*100);
        String monthString = "";
        String [] months_list = context.getResources().getStringArray(R.array.months_name_inflected);
        monthString = months_list[month-1];

        return monthString;
    }

    public String getDateStartEndStringText(Context context) {
        String text = context.getResources().getString(R.string.event_dates);
        String res = text + " ";

        String monthStart = getEventMonthInflectedString(context, dateBegin);
        Integer dayStart = dateBegin - ((dateBegin/100)*100);
        if (String.valueOf(dateEnd).length() == 8) {
            String monthEnd = getEventMonthInflectedString(context, dateEnd);
            Integer dayEnd = dateEnd - ((dateEnd / 100) * 100);

            if (monthStart.equals(monthEnd))
                res += String.valueOf(dayStart) + "-" + String.valueOf(dayEnd) + " " + monthStart;
            else
                res += String.valueOf(dayStart) + " " + monthStart + " - " + String.valueOf(dayEnd) + " " + monthEnd;
        }

        return res;
    }



}
