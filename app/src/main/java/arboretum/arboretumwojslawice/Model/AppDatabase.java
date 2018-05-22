package arboretum.arboretumwojslawice.Model;
import arboretum.arboretumwojslawice.Model.DAO.*;
import arboretum.arboretumwojslawice.Model.Entity.AttractionEntity;
import arboretum.arboretumwojslawice.Model.Entity.AttractionTranslationEntity;
import arboretum.arboretumwojslawice.Model.Entity.EventEntity;
import arboretum.arboretumwojslawice.Model.Entity.EventTranslationEntity;
import arboretum.arboretumwojslawice.Model.Entity.GenusEntity;
import arboretum.arboretumwojslawice.Model.Entity.HotelEntity;
import arboretum.arboretumwojslawice.Model.Entity.LocationEntity;
import arboretum.arboretumwojslawice.Model.Entity.NewsEntity;
import arboretum.arboretumwojslawice.Model.Entity.NewsTranslationEntity;
import arboretum.arboretumwojslawice.Model.Entity.PlantEntity;
import arboretum.arboretumwojslawice.Model.Entity.PlantTranslationEntity;
import arboretum.arboretumwojslawice.Model.Entity.PriceEntity;
import arboretum.arboretumwojslawice.Model.Entity.PriceTranslationEntity;
import arboretum.arboretumwojslawice.Model.Entity.RestaurantEntity;
import arboretum.arboretumwojslawice.Model.Entity.RouteEntity;
import arboretum.arboretumwojslawice.Model.Entity.RouteMapCoordEntity;
import arboretum.arboretumwojslawice.Model.Entity.RoutePointEntity;
import arboretum.arboretumwojslawice.Model.Entity.RouteTranslationEntity;
import arboretum.arboretumwojslawice.Model.Entity.SpeciesEntity;
import arboretum.arboretumwojslawice.Model.businessentity.RouteMapCoord;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


/**
 * Created by Komputer on 2018-03-23.
 */



@Database(entities = {AttractionEntity.class, AttractionTranslationEntity.class,
        EventEntity.class, EventTranslationEntity.class, GenusEntity.class, HotelEntity.class,
        LocationEntity.class, PlantEntity.class, PlantTranslationEntity.class,
        PriceEntity.class, PriceTranslationEntity.class, RestaurantEntity.class,
        RouteEntity.class, RoutePointEntity.class, RouteTranslationEntity.class,
        RouteMapCoordEntity.class,
        SpeciesEntity.class, NewsEntity.class, NewsTranslationEntity.class},
        version = 1)
    public abstract class AppDatabase extends RoomDatabase {
        public static final String DATABASE_NAME = "ArboretumDB";

        public abstract AttractionDao getAttractionDao();
        public abstract AttractionTranslationDao getAttractionTranslationDao();
        public abstract EventDao getEventDao();
        public abstract EventTranslationDao getEventTranslationDao();
        public abstract GenusDao getGenusDao();
        public abstract HotelDao getHotelDao();
        public abstract LocationDao getLocationDao();
        public abstract PlantDao getPlantDao();
        public abstract PlantTranslationDao getPlantTranslationDao();
        public abstract PriceDao getPriceDao();
        public abstract PriceTranslationDao getPriceTranslationDao();
        public abstract RestaurantDao getRestaurantDao();
        public abstract RouteDao getRouteDao();
        public abstract RouteMapCoordDao getRouteMapCoordDao();
        public abstract RoutePointDao getRoutePointDao();
        public abstract RouteTranslationDao getRouteTranslationDao();
        public abstract SpeciesDao getSpeciesDao();
        public abstract NewsDao getNewsDao();
        public abstract NewsTranslationDao getNewsTranslationDao();


    private static AppDatabase INSTANCE;


    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            .build();
        }
        return INSTANCE;
    }



}

