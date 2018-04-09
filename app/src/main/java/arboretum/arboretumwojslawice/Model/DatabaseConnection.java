package arboretum.arboretumwojslawice.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.AttractionEntity;
import arboretum.arboretumwojslawice.Model.Entity.AttractionTranslationEntity;
import arboretum.arboretumwojslawice.Model.Entity.PriceEntity;
import arboretum.arboretumwojslawice.Model.Entity.PriceTranslationEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Price;


public class DatabaseConnection extends AsyncTask<Void,Void,Boolean>
{
    private Context context;
    AppDatabase database;

    /* LISTS OF TABLES - ENTITIES */
    List<AttractionEntity> attractionEntities = new ArrayList<>();
    List<AttractionTranslationEntity> attractionTranslationEntities = new ArrayList<>();

    public DatabaseConnection(Context context, AppDatabase db) {
        this.context = context;
        this.database = db;
    }

    private void createData() {
//        attractionEntities.add(new AttractionEntity(1, context.getResources()));
    }

    protected Boolean doInBackground(Void... urls) {

        createData();
        Price p1 = new Price(1, 30.0, "normalny", "bilet dla normalnych ludzi");
        Price p2 = new Price(2, 10.0, "ulgowy", "bilet tylko za pokazaniem ważnego dokumentu potwierdzającego przysługujące ulgi");
        Price p3 = new Price(3, 15.0, "inny", "a jakiśtam inny, trzeci bilet");
        String translationCode = context.getResources().getConfiguration().locale.getLanguage();

        database.runInTransaction(new Runnable() {
            @Override
            public void run() {
                database.getPriceTranslationDao().deleteAll();
                database.getPriceDao().deleteAll();
                database.getPriceDao().insert(new PriceEntity(p1.getIdPrice(), p1.getAmount()),
                        new PriceEntity(p2.getIdPrice(), p2.getAmount()),
                        new PriceEntity(p3.getIdPrice(), p3.getAmount()));
                database.getPriceTranslationDao().insert(new PriceTranslationEntity(translationCode, p1.getIdPrice(), p1.getType(), p1.getDescription()),
                        new PriceTranslationEntity(translationCode, p2.getIdPrice(), p2.getType(), p2.getDescription()),
                        new PriceTranslationEntity(translationCode, p3.getIdPrice(), p3.getType(), p3.getDescription()));
            }
        });

        return true;
    }

    protected void onPostExecute(String result) {
        Toast.makeText(context, "Data in database inserted :)", Toast.LENGTH_LONG).show();
    }
}
