package arboretum.arboretumwojslawice.Commons.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import arboretum.arboretumwojslawice.Model.AppDatabase;
import arboretum.arboretumwojslawice.Model.DAO.AttractionDao;
import arboretum.arboretumwojslawice.Model.DAO.AttractionTranslationDao;
import arboretum.arboretumwojslawice.Model.DAO.EventDao;
import arboretum.arboretumwojslawice.Model.DAO.EventTranslationDao;
import arboretum.arboretumwojslawice.Model.DAO.FavouritePlantDao;
import arboretum.arboretumwojslawice.Model.DAO.GenusDao;
import arboretum.arboretumwojslawice.Model.DAO.HotelDao;
import arboretum.arboretumwojslawice.Model.DAO.KindDao;
import arboretum.arboretumwojslawice.Model.DAO.KindTranslationDao;
import arboretum.arboretumwojslawice.Model.DAO.LocationDao;
import arboretum.arboretumwojslawice.Model.DAO.NewsDao;
import arboretum.arboretumwojslawice.Model.DAO.NewsTranslationDao;
import arboretum.arboretumwojslawice.Model.DAO.PlantDao;
import arboretum.arboretumwojslawice.Model.DAO.PlantTranslationDao;
import arboretum.arboretumwojslawice.Model.DAO.PriceDao;
import arboretum.arboretumwojslawice.Model.DAO.PriceTranslationDao;
import arboretum.arboretumwojslawice.Model.DAO.RestaurantDao;
import arboretum.arboretumwojslawice.Model.DAO.RouteDao;
import arboretum.arboretumwojslawice.Model.DAO.RoutePointDao;
import arboretum.arboretumwojslawice.Model.DAO.RouteTranslationDao;
import arboretum.arboretumwojslawice.Model.DAO.SpeciesDao;
import arboretum.arboretumwojslawice.Model.Entity.AttractionTranslationEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Attraction;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Komputer on 2018-03-27.
 */

@Module
public class DatabaseModule {
    private AppDatabase appDatabase;

    public DatabaseModule(Application mApplication) {
        appDatabase = Room.databaseBuilder(mApplication, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
    }

    @Singleton
    @Provides
    AppDatabase providesAppDatabase() {
        return appDatabase;
    }

    @Singleton
    @Provides
    AttractionDao providesAttractionDao(AppDatabase appDatabase) {
        return appDatabase.getAttractionDao();
    }

    @Singleton
    @Provides
    AttractionTranslationDao providesAttractionTranslationDao(AppDatabase appDatabase) {
        return appDatabase.getAttractionTranslationDao();
    }

    @Singleton
    @Provides
    EventDao providesEventDao(AppDatabase appDatabase) {
        return appDatabase.getEventDao();
    }

    @Singleton
    @Provides
    EventTranslationDao providesEventTranslationDao(AppDatabase appDatabase) {
        return appDatabase.getEventTranslationDao();
    }

    @Singleton
    @Provides
    FavouritePlantDao providesFavouritePlantDao(AppDatabase appDatabase) {
        return appDatabase.getFavouritePlantDao();
    }

    @Singleton
    @Provides
    GenusDao providesGenusDao(AppDatabase appDatabase) {
        return appDatabase.getGenusDao();
    }

    @Singleton
    @Provides
    HotelDao providesHotelDao(AppDatabase appDatabase) {
        return appDatabase.getHotelDao();
    }

    @Singleton
    @Provides
    KindDao providesKindDao(AppDatabase appDatabase) {
        return appDatabase.getKindDao();
    }

    @Singleton
    @Provides
    KindTranslationDao providesKindTranslationDao(AppDatabase appDatabase) {
        return appDatabase.getKindTranslationDao();
    }

    @Singleton
    @Provides
    LocationDao providesLocationDao(AppDatabase appDatabase) {
        return appDatabase.getLocationDao();
    }

    @Singleton
    @Provides
    PlantDao providesPlantDao(AppDatabase appDatabase) {
        return appDatabase.getPlantDao();
    }

    @Singleton
    @Provides
    PlantTranslationDao providesPlantTranslationDao(AppDatabase appDatabase) {
        return appDatabase.getPlantTranslationDao();
    }

    @Singleton
    @Provides
    PriceDao providesPriceDao(AppDatabase appDatabase) {
        return appDatabase.getPriceDao();
    }

    @Singleton
    @Provides
    PriceTranslationDao providesPriceTranslationDao(AppDatabase appDatabase) {
        return appDatabase.getPriceTranslationDao();
    }

    @Singleton
    @Provides
    RestaurantDao providesRestaurantDao(AppDatabase appDatabase) {
        return appDatabase.getRestaurantDao();
    }

    @Singleton
    @Provides
    RouteDao providesRouteDao(AppDatabase appDatabase) {
        return appDatabase.getRouteDao();
    }

    @Singleton
    @Provides
    RoutePointDao providesRoutePointDao(AppDatabase appDatabase) {
        return appDatabase.getRoutePointDao();
    }

    @Singleton
    @Provides
    RouteTranslationDao providesRouteTranslationDao(AppDatabase appDatabase) {
        return appDatabase.getRouteTranslationDao();
    }

    @Singleton
    @Provides
    SpeciesDao providesSpeciesDao(AppDatabase appDatabase) {
        return appDatabase.getSpeciesDao();
    }

    @Singleton
    @Provides
    NewsDao providesNewsDao(AppDatabase appDatabase) {
        return appDatabase.getNewsDao();
    }

    @Singleton
    @Provides
    NewsTranslationDao providesNewsTranslationDao(AppDatabase appDatabase) {
        return appDatabase.getNewsTranslationDao();
    }

}
