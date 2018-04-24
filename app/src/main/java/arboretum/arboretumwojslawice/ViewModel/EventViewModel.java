package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import arboretum.arboretumwojslawice.Model.Repository.EventRepository;
import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;
import arboretum.arboretumwojslawice.Model.businessentity.Event;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;

/**
 * Created by weronika on 05.04.2018.
 */

public class EventViewModel {
    List<Event> mEvent;
    EventRepository mEventRepo;

    public EventViewModel() {
        mEventRepo = new EventRepository();
    }

    public List<Event> getData() {
        mEvent = mEventRepo.getEventsForMichal();
        return mEvent;
    }

    // return one event founded by id
    public Event getEventById(int plant_id) {
        getData();
        for(int i = 0; i < mEvent.size(); i++)
        {
            if(mEvent.get(i).getIdEvent() == plant_id){
                return mEvent.get(i);
            }
        }
        return mEvent.get(plant_id);
    }
}
