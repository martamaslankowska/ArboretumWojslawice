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

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;


/**
 * Created by Komputer on 2018-03-23.
 */



@Database(entities = {AttractionEntity.class, AttractionTranslationEntity.class, EventEntity.class,
        EventTranslationEntity.class, FavouritePlantEntity.class, GenusEntity.class, HotelEntity.class,
        KindEntity.class, KindTranslationEntity.class, LocationEntity.class, PlantEntity.class,
        PlantTranslationEntity.class, PriceEntity.class, PriceTranslationEntity.class, RestaurantEntity.class,
        RouteEntity.class, RoutePointEntity.class, RouteTranslationEntity.class, SpeciesEntity.class,
        NewsEntity.class, NewsTranslationEntity.class},
        version = 2)
    public abstract class AppDatabase extends RoomDatabase {
        public static final String DATABASE_NAME = "ArboretumDB";

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

    private static AppDatabase INSTANCE;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };



    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
//                            .addMigrations(MIGRATION_1_2)
                            .build();
        }
        return INSTANCE;
    }



}

