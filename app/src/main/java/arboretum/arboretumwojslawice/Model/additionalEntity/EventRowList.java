package arboretum.arboretumwojslawice.Model.additionalEntity;

import android.content.Context;

import arboretum.arboretumwojslawice.Commons.AdapterItem;
import arboretum.arboretumwojslawice.R;

public class EventRowList implements AdapterItem {

    private String eventDateString;
    private String eventNames;
    private Integer eventDateInteger;

    public EventRowList(Integer eventDateInteger, String eventNames) {
        this.eventDateInteger = eventDateInteger;
        setEventDateString(eventDateInteger);
        this.eventNames = eventNames;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    public String getEventDateString() {
        return eventDateString;
    }

    public void setEventDateString(Integer eventDateString) {

        Integer day = eventDateString - ((eventDateString/100)*100);
        Integer month = (eventDateString/100) - ((eventDateString/10000)*100);
        Integer year = eventDateString/10000;

        this.eventDateString = (day<10 ? "0" : "") + String.valueOf(day) + "." + (month<10 ? "0" : "") + String.valueOf(month);
    }

    public String getEventNames() {
        return eventNames;
    }

    public void setEventNames(String eventNames) {
        this.eventNames = eventNames;
    }

    public Integer getEventDateInteger() {
        return eventDateInteger;
    }

    public void setEventDateInteger(Integer eventDateInteger) {
        this.eventDateInteger = eventDateInteger;
    }

    public String getEventDayString() {
        Integer day = eventDateInteger - ((eventDateInteger/100)*100);
        return String.valueOf(day);
    }

    public String getEventMonthString(Context context) {
        Integer month = (eventDateInteger/100) - ((eventDateInteger/10000)*100);
        String monthString = "";
        String [] months_list = context.getResources().getStringArray(R.array.months_name);
        monthString = months_list[month-1];

        return monthString.toUpperCase();
    }


}
