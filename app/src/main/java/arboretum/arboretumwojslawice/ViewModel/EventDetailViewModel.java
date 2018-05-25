package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.EventRepository;
import arboretum.arboretumwojslawice.Model.additionalEntity.EventRowList;
import arboretum.arboretumwojslawice.Model.businessentity.Event;

public class EventDetailViewModel {
    @Inject
    protected EventRepository eventRepo;

    @Inject
    public EventDetailViewModel(){}

    // return one event founded by id
    public Event getEventById(int plant_id) {
        return eventRepo.getById(plant_id);
    }

    public String getAllEventNameInDate(int date) {
        List<Event> eventInTheDay;
        eventInTheDay = eventRepo.getAllDuringGivenDate(date);
        String names = "";
        for(int j = 0; j < eventInTheDay.size(); j++)
        {
            names += eventInTheDay.get(j).getType();

            if(eventInTheDay.size() - 1 - j != 0)
            {
                names += " / ";
            }
        }
        return names;
    }

    public String getDateString(int date) {
        Integer day = date - ((date/100)*100);
        Integer month = (date/100) - ((date/10000)*100);
        Integer year = date/10000;

        return (day<10 ? "0" : "") + String.valueOf(day) + "." + (month<10 ? "0" : "") + String.valueOf(month);
    }

    public EventRowList getEventRowList(int date) {
        EventRowList eventRowList;

        List<Event> eventInTheDay;
        eventInTheDay = eventRepo.getAllDuringGivenDate(date);
        String names = "";
        for(int j = 0; j < eventInTheDay.size(); j++)
        {
            names += eventInTheDay.get(j).getType();

            if(eventInTheDay.size() - 1 - j != 0)
            {
                names += " / ";
            }
        }
        eventRowList = new EventRowList(date, names);

        return eventRowList;
    }


    public List<Event> getAllDuringGivenDate(int date) {
        return eventRepo.getAllDuringGivenDate(date);
    }
}
