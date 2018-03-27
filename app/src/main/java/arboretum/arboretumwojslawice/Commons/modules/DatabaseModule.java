package arboretum.arboretumwojslawice.Commons.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import arboretum.arboretumwojslawice.Model.AppDatabase;
import arboretum.arboretumwojslawice.Model.DAO.AttractionDao;
import arboretum.arboretumwojslawice.Model.DAO.AttractionTranslationDao;
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
}
