package arboretum.arboretumwojslawice.Model.Repository;

import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Komputer on 2018-03-29.
 */

public class BaseRepository {

    @Inject
    @Named("locale")
    protected Locale locale;

    @Inject
    @Named("languageCode")
    protected String languageCode;

    public int getToday() {
        Date date = new Date();
        int day = date.getDate();
        int month = date.getMonth();
        int year = date.getYear();
        int result = 10000*(year+1900) + 100*(month+1) + day;
        return result;
    }

}
