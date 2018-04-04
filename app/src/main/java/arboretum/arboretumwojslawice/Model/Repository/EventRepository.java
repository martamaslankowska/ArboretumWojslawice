package arboretum.arboretumwojslawice.Model.Repository;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.DAO.EventDao;
import arboretum.arboretumwojslawice.Model.DAO.EventTranslationDao;
import arboretum.arboretumwojslawice.Model.businessentity.Event;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Komputer on 2018-03-25.
 */

public class EventRepository extends BaseRepository {

    private EventDao eventDao;
    private EventTranslationDao eventTranslationDao;

    @Inject
    public EventRepository(EventDao EventDao, EventTranslationDao EventTranslationDao, Locale locale) {
        this.eventDao = EventDao;
        this.eventTranslationDao = EventTranslationDao;
        this.locale = locale;
    }

    public Maybe<List<Event>> getAllEvents() {
        return eventDao.getAll();
    }

    public Single<Event> getById(int id) {
        return eventDao.getById(id);
    }

    public Maybe<List<Event>> getAllByDateBegin(int dateBegin) {
        return eventDao.getAllByDateBegin(dateBegin);
    }
    
}
