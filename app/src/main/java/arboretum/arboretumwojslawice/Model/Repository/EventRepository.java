package arboretum.arboretumwojslawice.Model.Repository;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.DAO.EventDao;
import arboretum.arboretumwojslawice.Model.DAO.EventTranslationDao;
import arboretum.arboretumwojslawice.Model.businessentity.Event;

/**
 * Created by Komputer on 2018-03-25.
 */

public class EventRepository extends BaseRepository {

    @Inject
    EventDao eventDao;

    @Inject
    EventTranslationDao eventTranslationDao;

    @Inject
    public EventRepository() {}

    public List<Event> getAllEvents() {
        return eventDao.getAll(languageCode);
    }

    public Event getById(int id) {
        return eventDao.getById(id, languageCode);
    }

    public List<Event> getAllByDateBegin(int dateBegin) {
        return eventDao.getAllByDateBegin(dateBegin, languageCode);
    }

    public List<Event> getAllDuringGivenDate(int date) {
        return eventDao.getAllDuringGivenDate(date, languageCode);
    }

    public List<Integer> getAllDateBegin() {
        return eventDao.getAllDateBegin();
    }

    public List<Integer> getAllDateFromToday() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyyMMdd");
        String strDate = mdformat.format(calendar.getTime());

        Integer day = Integer.valueOf(strDate.substring(6, 8));
        Integer month = Integer.valueOf(strDate.substring(4, 6));
        Integer year = Integer.valueOf(strDate.substring(0, 4));
        Integer IntegerDate = 10000*year + 100*month + day;

        List<Integer> result = eventDao.getAllDateFromToday(IntegerDate, languageCode);

        return result;
    }
}
