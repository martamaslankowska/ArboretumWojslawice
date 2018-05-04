package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;

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
    public EventViewModel() { mEventRepo = new EventRepository(); }

    public List<Event> getData() {
        mEvent = mEventRepo.getEventsForMichal();
        return mEvent;
    }
}
