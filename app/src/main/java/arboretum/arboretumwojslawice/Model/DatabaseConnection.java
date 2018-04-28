package arboretum.arboretumwojslawice.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Model.Entity.AttractionEntity;
import arboretum.arboretumwojslawice.Model.Entity.AttractionTranslationEntity;
import arboretum.arboretumwojslawice.Model.Entity.HotelEntity;
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
    List<HotelEntity> hotelEntities = new ArrayList<>();


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
//
//    private void createData() {
//        /* Attractions */
//        attractionEntities.add(new AttractionEntity(1, "news1", 23.0));
//        attractionEntities.add(new AttractionEntity(2, "news2", 45.0));
//        attractionEntities.add(new AttractionEntity(3, "news3", 53.0));
//        attractionTranslationEntities.add(new AttractionTranslationEntity(POLISH, 1, "Niemcza", "Średniowieczne miasto, położone niecałe 2 km od Wojsławic. Jest zabytkiem urbanistycznym i należy do najstarszych miast w Polsce.    W 2017 roku Niemcza będzie obchodziła 1000-lecie wielkiej bitwy obrony grodu z 1017 roku. Zapraszamy do zapoznania się z legendą: www.um.niemcza.pl/images/stories/ulotki/Niemcza/index.html"));
//        attractionTranslationEntities.add(new AttractionTranslationEntity(POLISH, 2, "Uzdrowisko Przerzeczyn Zdrój","Najmniejsze na Śląsku, wiejskie uzdrowisko oddalone 6 km od Wojsławic. Znane od 1802 r. z ciepłych źródeł radanowo-siarczkowych. Warto zobaczyć także zabytkowy kościół z XIII w. i mauzoleum rodu Oheimbów, z którego pochodził założyciel naszego Arboretum."));
//        attractionTranslationEntities.add(new AttractionTranslationEntity(POLISH, 3, "Krzywa Wieża w Ząbkowicach Śląskich","W odległości 15 km od Arboretum w Wojsławicach znajduje się 34-metrowa wieża dawnej dzwonnicy.       Jest prawdopodobnie pozostałością zamku z XV w. Obecnie pełni funkcję turystycznego punktu widokowego. Jej odchylenie od pionu wynosi już 2,14 m (w Pizie ok. 5 m). Tuż obok znajduje się Izba Pamiątek z Laboratorium dr. Frankensteina, która nawiązuję do pewnych tajemniczych wydarzeń z XVII w. Warto dodać, że miasto Ząbkowice nosiło dawniej nazwę Frankenstein."));
//
//        /*...*/
//
//        /* Prices */
//        priceEntities.add(new PriceEntity(1, 15, 0));
//        priceEntities.add(new PriceEntity(2, 10, 0));
////        priceEntities.add(new PriceEntity(3, 10, 0));
////        priceEntities.add(new PriceEntity(4, 10, 0));
////        priceEntities.add(new PriceEntity(5, 10, 0));
//        priceEntities.add(new PriceEntity(10, 60, 0));
//        priceEntities.add(new PriceEntity(11, 40, 0));
//        priceTranslationEntities.add(new PriceTranslationEntity(POLISH, 1, "normalny", null));
//        priceTranslationEntities.add(new PriceTranslationEntity(POLISH, 2, "ulgowy", "za okazaniem stosownych dokumentów\n- uczniowie (z wyłączeniem szkół dla dorosłych)\n- studenci\n- osoby niepełnosprawne"));
////        priceTranslationEntities.add(new PriceTranslationEntity(POLISH, 3, "ulgowy", "uczniowie (z wyłączeniem szkół dla dorosłych)"));
////        priceTranslationEntities.add(new PriceTranslationEntity(POLISH, 4, "ulgowy", "studenci"));
////        priceTranslationEntities.add(new PriceTranslationEntity(POLISH, 5, "ulgowy", "osoby niepełnosprawne"));
//        priceTranslationEntities.add(new PriceTranslationEntity(POLISH, 10, "wielokrotny normalny", "5 wejść w cenie 4; 1 dzień = 1 wejście\n"));
//        priceTranslationEntities.add(new PriceTranslationEntity(POLISH, 11,"wielokrotny ulgowy", "5 wejść w cenie 4; 1 dzień = 1 wejście\n"));
//        priceTranslationEntities.add(new PriceTranslationEntity(ENGLISH, 1, "standard", null));
//        priceTranslationEntities.add(new PriceTranslationEntity(ENGLISH, 2, "reduced", "after showing relevant documents\n- school students\n- students\n- disabled people"));
////        priceTranslationEntities.add(new PriceTranslationEntity(ENGLISH, 3, "reduced", "school students"));
////        priceTranslationEntities.add(new PriceTranslationEntity(ENGLISH, 4, "reduced", "students"));
////        priceTranslationEntities.add(new PriceTranslationEntity(ENGLISH, 5, "reduced", "disabled people"));
//        priceTranslationEntities.add(new PriceTranslationEntity(ENGLISH, 10, "reusable standard", "5 for the price of 4; 1 day = 1 enter"));
//        priceTranslationEntities.add(new PriceTranslationEntity(ENGLISH, 11, "reusable reduced", "5 for the price of 4; 1 day = 1 enter"));
//
//
//        /*...*/
//
//        hotelEntities.add(new HotelEntity(1, "Hotel z bazy danych - oł jeee", "", 0, "", 0.0, 5.0, "news1"));
//
//    }

    protected Boolean doInBackground(Void... urls) {
//        createData();

        database.runInTransaction(new Runnable() {
            @Override
            public void run() {

                /* DELETE ALL - in a different order than insert; first delete translation, then entity */
                database.getPriceTranslationDao().deleteAll();
                database.getPriceDao().deleteAll();

                /* INSERT ALL - first insert to entity, then to translation */
                database.getPriceDao().insert(priceEntities);
                database.getPriceTranslationDao().insert(priceTranslationEntities);
                database.getAttractionDao().insert(attractionEntities);
                database.getAttractionTranslationDao().insert(attractionTranslationEntities);
                database.getHotelDao().insert(hotelEntities);

            }
        });

        return true;
    }

    protected void onPostExecute(String result) {
        Toast.makeText(context, "Data in database inserted :)", Toast.LENGTH_LONG).show();
    }
}
