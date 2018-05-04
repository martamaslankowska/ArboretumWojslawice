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
        getData();
        for(int i = 0; i < mEvent.size(); i++) {
            if(mEvent.get(i).getIdEvent() == plant_id){
                return mEvent.get(i);
            }
        }
        return mEvent.get(plant_id);
    }
}