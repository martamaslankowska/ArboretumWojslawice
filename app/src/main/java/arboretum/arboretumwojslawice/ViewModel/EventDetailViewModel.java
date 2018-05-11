package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.EventRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Event;

public class EventDetailViewModel {
    private List<Event> mEvent;
    @Inject
    protected EventRepository mEventRepo;

    @Inject
    public EventDetailViewModel(){}

    public List<Event> getData() {
        mEvent = mEventRepo.getEventsForMichal();
        return mEvent;
    }

    // return one event founded by id
    public Event getEventById(int plant_id) {
        return mEventRepo.getById(plant_id);
    }

    public String getAllEventNameInDate(int date) {
        List<Event> eventInTheDay;
        eventInTheDay = mEventRepo.getAllDuringGivenDate(date);
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


    public List<Event> getAllDuringGivenDate(int date) {
        return mEventRepo.getAllDuringGivenDate(date);
    }
}
