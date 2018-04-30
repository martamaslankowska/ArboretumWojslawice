package arboretum.arboretumwojslawice.Model.Repository;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import arboretum.arboretumwojslawice.Model.businessentity.Attraction;
import io.reactivex.Maybe;

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

}
