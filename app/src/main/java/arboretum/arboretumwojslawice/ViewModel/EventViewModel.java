package arboretum.arboretumwojslawice.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.AdditionalEntity.EventRowList;
import arboretum.arboretumwojslawice.Model.Repository.EventRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Event;

/**
 * Created by weronika on 05.04.2018.
 */

public class EventViewModel {
    private List<Event> mEvent;

    @Inject
    protected EventRepository mEventRepo;

    @Inject
    public EventViewModel() {}

    public List<Event> getData() {
        mEvent = mEventRepo.getEventsForMichal();
        return mEvent;
    }

    public List<Event> getAllEvents() {
        mEvent = mEventRepo.getAllEvents();
        return mEvent;
    }



    public List<EventRowList> getAllDateBegin() {
        List<Integer> dateBeginInteger = mEventRepo.getAllDateBegin();
        List<EventRowList> eventList = new ArrayList<EventRowList>();
        EventRowList eventRowList;
        List<Event> eventInTheDay;

        for(int i = 0; i < dateBeginInteger.size(); i++)
        {
            eventInTheDay = mEventRepo.getAllDuringGivenDate(dateBeginInteger.get(i));
            String names = "";
            for(int j = 0; j < eventInTheDay.size(); j++)
            {
                names += eventInTheDay.get(j).getType();

                if(eventInTheDay.size() - 1 - j != 0)
                {
                    names += " / ";
                }
            }
            eventRowList = new EventRowList(dateBeginInteger.get(i), names);
            eventList.add(eventRowList);
        }

        String names;



        return eventList;
    }

    public List<Event> getAllDuringGivenDate(int date) {
        return mEventRepo.getAllDuringGivenDate(date);
    }


}
