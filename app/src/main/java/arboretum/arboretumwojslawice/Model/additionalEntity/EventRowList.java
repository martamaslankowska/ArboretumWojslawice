package arboretum.arboretumwojslawice.Model.additionalEntity;

import arboretum.arboretumwojslawice.Commons.AdapterItem;

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

    public String getEventMonthString() {
        Integer month = (eventDateInteger/100) - ((eventDateInteger/10000)*100);
        String monthString = "";

        switch(month)
        {
            case 1: monthString = "Styczeń"; break;
            case 2: monthString = "Luty"; break;
            case 3: monthString = "Marzec"; break;
            case 4: monthString = "Kwiecień"; break;
            case 5: monthString = "Maj"; break;
            case 6: monthString = "Czerwiec"; break;
            case 7: monthString = "Lipiec"; break;
            case 8: monthString = "Sierpień"; break;
            case 9: monthString = "Wrzesień"; break;
            case 10: monthString = "Październik"; break;
            case 11: monthString = "Listopad"; break;
            case 12: monthString = "Grudzień"; break;
        }

        return monthString;
    }


}
