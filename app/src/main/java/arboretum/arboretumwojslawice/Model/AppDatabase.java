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
        public static final String DATABASE_NAME = "MyArboretumDB";

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
            database.execSQL("DROP TABLE IF EXISTS `Attractions`");
            database.execSQL("DROP TABLE IF EXISTS `AttractionsTranslations`");
            database.execSQL("DROP TABLE IF EXISTS `Events`");
            database.execSQL("DROP TABLE IF EXISTS `EventsTranslations`");
            database.execSQL("DROP TABLE IF EXISTS `FavouritePlants`");
            database.execSQL("DROP TABLE IF EXISTS `Genus`");
            database.execSQL("DROP TABLE IF EXISTS `Hotels`");
            database.execSQL("DROP TABLE IF EXISTS `Kinds`");
            database.execSQL("DROP TABLE IF EXISTS `KindsTranslations`");
            database.execSQL("DROP TABLE IF EXISTS `Locations`");
            database.execSQL("DROP TABLE IF EXISTS `Plants`");
            database.execSQL("DROP TABLE IF EXISTS `PlantsTranslations`");
            database.execSQL("DROP TABLE IF EXISTS `Prices`");
            database.execSQL("DROP TABLE IF EXISTS `PricesTranslations`");
            database.execSQL("DROP TABLE IF EXISTS `Restaurants`");
            database.execSQL("DROP TABLE IF EXISTS `Routes`");
            database.execSQL("DROP TABLE IF EXISTS `RoutePoints`");
            database.execSQL("DROP TABLE IF EXISTS `RoutesTranslations`");
            database.execSQL("DROP TABLE IF EXISTS `Species`");
            database.execSQL("DROP TABLE IF EXISTS `News`");
            database.execSQL("DROP TABLE IF EXISTS `NewsTranslations`");

            database.execSQL("CREATE TABLE IF NOT EXISTS `Attractions` (`IdAttraction` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Image` INTEGER NOT NULL)");
            database.execSQL("CREATE TABLE IF NOT EXISTS `AttractionsTranslations` (`TranslationCode` TEXT NOT NULL, `IdAttraction` INTEGER NOT NULL, `Name` TEXT NOT NULL, `Description` TEXT NOT NULL, PRIMARY KEY(`TranslationCode`, `IdAttraction`), FOREIGN KEY(`IdAttraction`) REFERENCES `Attractions`(`IdAttraction`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
            database.execSQL("CREATE TABLE IF NOT EXISTS `Events` (`IdEvent` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `DateBegin` INTEGER NOT NULL, `DateEnd` INTEGER NOT NULL, `TimeBegin` INTEGER NOT NULL, `TimeEnd` INTEGER NOT NULL)");
            database.execSQL("CREATE TABLE IF NOT EXISTS `EventsTranslations` (`TranslationCode` TEXT NOT NULL, `IdEvent` INTEGER NOT NULL, `Type` TEXT, `Name` TEXT NOT NULL, `Description` TEXT, PRIMARY KEY(`TranslationCode`, `IdEvent`), FOREIGN KEY(`IdEvent`) REFERENCES `Events`(`IdEvent`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
            database.execSQL("CREATE TABLE IF NOT EXISTS `FavouritePlants` (`IdPlant` INTEGER NOT NULL, PRIMARY KEY(`IdPlant`), FOREIGN KEY(`IdPlant`) REFERENCES `Plants`(`IdPlant`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
            database.execSQL("CREATE TABLE IF NOT EXISTS `Genus` (`Name` INTEGER NOT NULL, PRIMARY KEY(`Name`))");
            database.execSQL("CREATE TABLE IF NOT EXISTS `Hotels` (`IdHotel` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Name` TEXT, `Address` TEXT, `Phone` INTEGER NOT NULL, `Website` TEXT, `Distance` REAL NOT NULL, `Rating` REAL NOT NULL, `Image` INTEGER NOT NULL)");
            database.execSQL("CREATE TABLE IF NOT EXISTS `Kinds` (`IdKind` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
            database.execSQL("CREATE TABLE IF NOT EXISTS `KindsTranslations` (`TranslationCode` TEXT NOT NULL, `IdKind` INTEGER NOT NULL, `Name` TEXT NOT NULL, PRIMARY KEY(`TranslationCode`, `IdKind`), FOREIGN KEY(`IdKind`) REFERENCES `Kinds`(`IdKind`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
            database.execSQL("CREATE TABLE IF NOT EXISTS `Locations` (`IdLocation` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `IdPlant` INTEGER NOT NULL, `X` REAL NOT NULL, `Y` REAL NOT NULL, FOREIGN KEY(`IdPlant`) REFERENCES `Plants`(`IdPlant`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
            database.execSQL("CREATE TABLE IF NOT EXISTS `Plants` (`IdPlant` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `LatinName` TEXT, `IdSpecies` INTEGER NOT NULL, `IdKind` INTEGER NOT NULL, `Image` INTEGER NOT NULL, `SeasonBegin` INTEGER NOT NULL, `SeasonEnd` INTEGER NOT NULL, FOREIGN KEY(`IdKind`) REFERENCES `Kinds`(`IdKind`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`IdSpecies`) REFERENCES `Species`(`IdSpecies`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
            database.execSQL("CREATE TABLE IF NOT EXISTS `PlantsTranslations` (`TranslationCode` TEXT NOT NULL, `IdPlant` INTEGER NOT NULL, `Name` TEXT, `Description` TEXT, PRIMARY KEY(`TranslationCode`, `IdPlant`), FOREIGN KEY(`IdPlant`) REFERENCES `Plants`(`IdPlant`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
            database.execSQL("CREATE TABLE IF NOT EXISTS `Prices` (`IdPrice` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Amount` REAL NOT NULL)");
            database.execSQL("CREATE TABLE IF NOT EXISTS `PricesTranslations` (`TranslationCode` TEXT NOT NULL, `IdPrice` INTEGER NOT NULL, `Type` TEXT NOT NULL, `Description` TEXT, PRIMARY KEY(`TranslationCode`, `IdPrice`), FOREIGN KEY(`IdPrice`) REFERENCES `Prices`(`IdPrice`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
            database.execSQL("CREATE TABLE IF NOT EXISTS `Restaurants` (`IdRestaurant` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Name` TEXT, `Address` TEXT, `Phone` INTEGER NOT NULL, `Website` TEXT, `Distance` REAL NOT NULL, `Rating` REAL NOT NULL, `Image` INTEGER NOT NULL)");
            database.execSQL("CREATE TABLE IF NOT EXISTS `Routes` (`IdRoute` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Length` REAL NOT NULL, `Time` INTEGER NOT NULL, `MapImage` INTEGER NOT NULL, `MapImageDetailed` INTEGER NOT NULL)");
            database.execSQL("CREATE TABLE IF NOT EXISTS `RoutePoints` (`IdRoute` INTEGER NOT NULL, `IdLocation` INTEGER NOT NULL, `PointOrder` INTEGER NOT NULL, PRIMARY KEY(`IdRoute`, `IdLocation`), FOREIGN KEY(`IdRoute`) REFERENCES `Routes`(`IdRoute`) ON UPDATE NO ACTION ON DELETE NO ACTION , FOREIGN KEY(`IdLocation`) REFERENCES `Locations`(`IdLocation`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
            database.execSQL("CREATE TABLE IF NOT EXISTS `RoutesTranslations` (`TranslationCode` TEXT NOT NULL, `IdRoute` INTEGER NOT NULL, `Name` TEXT NOT NULL, `Description` TEXT, PRIMARY KEY(`TranslationCode`, `IdRoute`), FOREIGN KEY(`IdRoute`) REFERENCES `Routes`(`IdRoute`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
            database.execSQL("CREATE TABLE IF NOT EXISTS `Species` (`IdSpecies` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `GenusName` TEXT, `SpeciesName` TEXT, FOREIGN KEY(`GenusName`) REFERENCES `Genus`(`Name`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
            database.execSQL("CREATE TABLE IF NOT EXISTS `News` (`IdNews` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Image` INTEGER NOT NULL, `Date` INTEGER NOT NULL)");
            database.execSQL("CREATE TABLE IF NOT EXISTS `NewsTranslations` (`TranslationCode` TEXT NOT NULL, `IdNews` INTEGER NOT NULL, `Name` TEXT NOT NULL, `Description` TEXT, PRIMARY KEY(`TranslationCode`, `IdNews`), FOREIGN KEY(`IdNews`) REFERENCES `News`(`IdNews`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        }
    };

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                            .addMigrations(MIGRATION_1_2)
//                            .allowMainThreadQueries() // DELETE after tests
                            .build();
        }
        return INSTANCE;
    }



}

