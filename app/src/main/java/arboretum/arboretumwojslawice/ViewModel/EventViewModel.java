package arboretum.arboretumwojslawice.ViewModel;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.additionalEntity.EventRowList;
import arboretum.arboretumwojslawice.Model.Repository.EventRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Event;

/**
 * Created by weronika on 05.04.2018.
 */

public class EventViewModel {

    @Inject
    protected EventRepository eventRepo;

    @Inject
    public EventViewModel() {}

    public List<EventRowList> getAllDateBegin() {
        List<Integer> dateBeginInteger = eventRepo.getAllDateFromToday();
        List<EventRowList> eventList = new ArrayList<EventRowList>();
        EventRowList eventRowList;
        List<Event> eventInTheDay;

        for(int i = 0; i < dateBeginInteger.size(); i++)
        {
            eventInTheDay = eventRepo.getAllDuringGivenDate(dateBeginInteger.get(i));
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
            Log.d("Dzien " + i, eventRowList.getEventDateString());
            eventList.add(eventRowList);
        }

        return eventList;
    }

    public List<Event> getAllDuringGivenDate(int date) {
        return eventRepo.getAllDuringGivenDate(date);
    }



}
