package arboretum.arboretumwojslawice.Model;
import arboretum.arboretumwojslawice.Model.DAO.*;
import arboretum.arboretumwojslawice.Model.Entity.AttractionEntity;
import arboretum.arboretumwojslawice.Model.Entity.AttractionTranslationEntity;
import arboretum.arboretumwojslawice.Model.Entity.EventEntity;
import arboretum.arboretumwojslawice.Model.Entity.EventTranslationEntity;
import arboretum.arboretumwojslawice.Model.Entity.FavouritePlantEntity;
import arboretum.arboretumwojslawice.Model.Entity.GenusEntity;
import arboretum.arboretumwojslawice.Model.Entity.HotelEntity;
import arboretum.arboretumwojslawice.Model.Entity.KindEntity;
import arboretum.arboretumwojslawice.Model.Entity.KindTranslationEntity;
import arboretum.arboretumwojslawice.Model.Entity.LocationEntity;
import arboretum.arboretumwojslawice.Model.Entity.NewsEntity;
import arboretum.arboretumwojslawice.Model.Entity.NewsTranslationEntity;
import arboretum.arboretumwojslawice.Model.Entity.PlantEntity;
import arboretum.arboretumwojslawice.Model.Entity.PlantTranslationEntity;
import arboretum.arboretumwojslawice.Model.Entity.PriceEntity;
import arboretum.arboretumwojslawice.Model.Entity.PriceTranslationEntity;
import arboretum.arboretumwojslawice.Model.Entity.RestaurantEntity;
import arboretum.arboretumwojslawice.Model.Entity.RouteEntity;
import arboretum.arboretumwojslawice.Model.Entity.RoutePointEntity;
import arboretum.arboretumwojslawice.Model.Entity.RouteTranslationEntity;
import arboretum.arboretumwojslawice.Model.Entity.SpeciesEntity;
import arboretum.arboretumwojslawice.Model.businessentity.News;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


/**
 * Created by Komputer on 2018-03-23.
 */



@Database(entities = {AttractionEntity.class, AttractionTranslationEntity.class, EventEntity.class,
        EventTranslationEntity.class, FavouritePlantEntity.class, GenusEntity.class, HotelEntity.class,
        KindEntity.class, KindTranslationEntity.class, LocationEntity.class, PlantEntity.class,
        PlantTranslationEntity.class, PriceEntity.class, PriceTranslationEntity.class, RestaurantEntity.class,
        RouteEntity.class, RoutePointEntity.class, RouteTranslationEntity.class, SpeciesEntity.class,
        NewsEntity.class, NewsTranslationEntity.class},
        version = 1)
    public abstract class AppDatabase extends RoomDatabase {
        public static final String DATABASE_NAME = "ArboretumDatabase";

        public abstract AttractionDao getAttractionDao();
        public abstract AttractionTranslationDao getAttractionTranslationDao();
        public abstract EventDao getEventDao();
        public abstract EventTranslationDao getEventTranslationDao();
        public abstract FavouritePlantDao getFavouritePlantDao();
        public abstract GenusDao getGenusDao();
        public abstract HotelDao getHotelDao();
        public abstract KindDao getKindDao();
        public abstract KindTranslationDao getKindTranslationDao();
        public abstract LocationDao getLocationDao();
        public abstract PlantDao getPlantDao();
        public abstract PlantTranslationDao getPlantTranslationDao();
        public abstract PriceDao getPriceDao();
        public abstract PriceTranslationDao getPriceTranslationDao();
        public abstract RestaurantDao getRestaurantDao();
        public abstract RouteDao getRouteDao();
        public abstract RoutePointDao getRoutePointDao();
        public abstract RouteTranslationDao getRouteTranslationDao();
        public abstract SpeciesDao getSpeciesDao();
        public abstract NewsDao getNewsDao();
        public abstract NewsTranslationDao getNewsTranslationDao();


//    /* Some copied code from https://medium.com/@ajaysaini.official/building-database-with-room-persistence-library-ecf7d0b8f3e9
//     * Here's also an different example: https://medium.com/@alahammad/database-with-room-using-rxjava-764ee6124974
//     * Still working on this method (especially thread) */
//
//    public static AppDatabase getAppDatabase(Context context) {
//        if (INSTANCE == null) {
//            INSTANCE =  Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "ArboretumDatabase")
//                            // allow queries on the main thread.
//                            // Don't do this on a real app! See PersistenceBasicSample for an example.
////                            .allowMainThreadQueries()
//                            .build();
//        }
//        return INSTANCE;
//    }
//
//    public static void destroyInstance() {
//        INSTANCE = null;
//    }

}

