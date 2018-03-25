package arboretum.arboretumwojslawice.Model;
import arboretum.arboretumwojslawice.Model.DAO.*;
import arboretum.arboretumwojslawice.Model.Entity.*;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


/**
 * Created by Komputer on 2018-03-23.
 */



//@Database(entities = {
//            PlantEntity.class, FavouritePlantEntity.class, KindEntity.class,
//            LocationEntity.class, RoutePointEntity.class, RouteEntity.class},
//          version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;


    public abstract PlantDao getPlantDao();
    public abstract FavouritePlantDao getFavouritePlantDao();
    public abstract KindDao getKindDao();
    public abstract LocationDao getLocationDao();
    public abstract RoutePointDao getRoutePointDao();
    public abstract RouteDao getRouteDao();
//
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

