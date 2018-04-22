package arboretum.arboretumwojslawice.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
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
    String language; //translationCode
    final String POLISH = "pl";
    final String ENGLISH = "en";

    /* LISTS OF TABLES - ENTITIES */
    List<AttractionEntity> attractionEntities = new ArrayList<>();
    List<AttractionTranslationEntity> attractionTranslationEntities = new ArrayList<>();
    /*...*/
    List<PriceEntity> priceEntities = new ArrayList<>();
    List<PriceTranslationEntity> priceTranslationEntities = new ArrayList<>();
    /*...*/


    public DatabaseConnection(Context context, AppDatabase db) {
        this.context = context;
        this.database = db;
        language = context.getResources().getConfiguration().locale.getLanguage();
    }

    private void deleteAllData() {
        Log.w("DatabaseConnection", "Weszło mi w deleteAllFiles() - choć i tak to bez sensu, bo baza jest tworzona od nowa... ;)");
        database.getPriceTranslationDao().deleteAll();
        database.getPriceDao().deleteAll();
    }

    private void createData() {
        /* Attractions */
        attractionEntities.add(new AttractionEntity(1, 0));
        attractionEntities.add(new AttractionEntity(2, 0));
        attractionEntities.add(new AttractionEntity(3, 0));
        attractionTranslationEntities.add(new AttractionTranslationEntity(POLISH, 1, "Niemcza", "Średniowieczne miasto, położone niecałe 2 km od Wojsławic. Jest zabytkiem urbanistycznym i należy do najstarszych miast w Polsce.    W 2017 roku Niemcza będzie obchodziła 1000-lecie wielkiej bitwy obrony grodu z 1017 roku. Zapraszamy do zapoznania się z legendą: www.um.niemcza.pl/images/stories/ulotki/Niemcza/index.html"));
        attractionTranslationEntities.add(new AttractionTranslationEntity(POLISH, 2, "Uzdrowisko Przerzeczyn Zdrój",""));
        attractionTranslationEntities.add(new AttractionTranslationEntity(POLISH, 3, "Krzywa Wieża w Ząbkowicach Śląskich","W odległości 15 km od Arboretum w Wojsławicach znajduje się 34-metrowa wieża dawnej dzwonnicy.       Jest prawdopodobnie pozostałością zamku z XV w. Obecnie pełni funkcję turystycznego punktu widokowego. Jej odchylenie od pionu wynosi już 2,14 m (w Pizie ok. 5 m). Tuż obok znajduje się Izba Pamiątek z Laboratorium dr. Frankensteina, która nawiązuję do pewnych tajemniczych wydarzeń z XVII w. Warto dodać, że miasto Ząbkowice nosiło dawniej nazwę Frankenstein."));

        /*...*/

        /* Prices */
        priceEntities.add(new PriceEntity(1, 15));
        priceEntities.add(new PriceEntity(2, 10));
        priceEntities.add(new PriceEntity(21, 10));
        priceEntities.add(new PriceEntity(22, 10));
        priceEntities.add(new PriceEntity(23, 10));
        priceEntities.add(new PriceEntity(3, 60));
        priceEntities.add(new PriceEntity(4, 40));
        priceTranslationEntities.add(new PriceTranslationEntity(POLISH, 1, "normalny", null));
        priceTranslationEntities.add(new PriceTranslationEntity(POLISH, 2, "ulgowy", "za okazaniem stosownych dokumentów"));
        priceTranslationEntities.add(new PriceTranslationEntity(POLISH, 21, "ulgowy", "uczniowie (z wyłączeniem szkół dla dorosłych)"));
        priceTranslationEntities.add(new PriceTranslationEntity(POLISH, 22, "ulgowy", "studenci"));
        priceTranslationEntities.add(new PriceTranslationEntity(POLISH, 23, "ulgowy", "osoby niepełnosprawne"));
        priceTranslationEntities.add(new PriceTranslationEntity(POLISH, 3, "wielokrotny normalny", "5 wejść w cenie 4; 1 dzień = 1 wejście\n"));
        priceTranslationEntities.add(new PriceTranslationEntity(POLISH, 4, "wielokrotny ulgowy", "5 wejść w cenie 4; 1 dzień = 1 wejście\n"));
        priceTranslationEntities.add(new PriceTranslationEntity(ENGLISH, 1, "standard", null));
        priceTranslationEntities.add(new PriceTranslationEntity(ENGLISH, 2, "reduced", "after showing revelant documents"));
        priceTranslationEntities.add(new PriceTranslationEntity(ENGLISH, 21, "reduced", "school students"));
        priceTranslationEntities.add(new PriceTranslationEntity(ENGLISH, 22, "reduced", "students"));
        priceTranslationEntities.add(new PriceTranslationEntity(ENGLISH, 23, "reduced", "disabled people"));
        priceTranslationEntities.add(new PriceTranslationEntity(ENGLISH, 3, "reusable normal", "5 for the price of 4; 1 day = 1 enter"));
        priceTranslationEntities.add(new PriceTranslationEntity(ENGLISH, 4, "reusable reduced", "5 for the price of 4; 1 day = 1 enter"));


        /*...*/
    }

    protected Boolean doInBackground(Void... urls) {
        createData();

        database.runInTransaction(new Runnable() {
            @Override
            public void run() {

                /* DELETE ALL - in a different order than insert; first delete translation, then entity */
                database.getPriceTranslationDao().deleteAll();
                database.getPriceDao().deleteAll();

                /* INSERT ALL - first insert to entity, then to translation */
                database.getPriceDao().insert(priceEntities);
                database.getPriceTranslationDao().insert(priceTranslationEntities);
            }
        });

        return true;
    }

    protected void onPostExecute(String result) {
        Toast.makeText(context, "Data in database inserted :)", Toast.LENGTH_LONG).show();
    }
}
