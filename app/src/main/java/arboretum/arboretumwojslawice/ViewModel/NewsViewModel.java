package arboretum.arboretumwojslawice.ViewModel;

import android.content.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.Repository.EventRepository;
import arboretum.arboretumwojslawice.Model.Repository.NewsRepository;
import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;
import arboretum.arboretumwojslawice.Model.additionalEntity.EventRowList;
import arboretum.arboretumwojslawice.Model.businessentity.Event;
import arboretum.arboretumwojslawice.Model.businessentity.News;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;

public class NewsViewModel {

    @Inject
    NewsRepository newsRepository;
    @Inject
    PlantRepository plantRepository;
    @Inject
    EventRepository eventRepository;

    @Inject
    public NewsViewModel() {

    }

    public News getCurrentNews() {
        return newsRepository.getAllPastNews().get(0);
    }

    public Plant getRandomSeasonPlant() {
        int today = plantRepository.getToday();
        List<Plant> plants = plantRepository.getAllSeasonPlants(today);
        Random r = new Random();
        int index = r.nextInt(plants.size());
        return plants.get(index);
    }

    public EventRowList getEventNameConcatenate(Context context) {
        String eventNames = "";
        String ampersand = context.getString(getStringResource(context, "ampersand"));

        int nearestDate = eventRepository.getAllDateFromToday().get(0);
        List<Event> eventsDuringNearestDate = eventRepository.getAllDuringGivenDate(nearestDate);
        int nrOfEvents = eventsDuringNearestDate.size();

        if (nrOfEvents > 0) {
            if (nrOfEvents > 1) {
                for (int i = 0; i < nrOfEvents; i++) {
                    if (i!= 0 && i != (nrOfEvents - 1))
                        eventNames += ",";
                    if (i == (nrOfEvents - 1))
                        eventNames += " " + ampersand + " " + eventsDuringNearestDate.get(i).getType().toLowerCase();
                    else
                        eventNames += " " + eventsDuringNearestDate.get(i).getType().toLowerCase();
                }
            } else {
                eventNames = eventsDuringNearestDate.get(0).getType().toLowerCase();
            }
        }

        return new EventRowList(nearestDate, eventNames);
    }

    private int getStringResource(Context context, String stringName) {
        return context.getResources().getIdentifier(stringName, "string", "arboretum.arboretumwojslawice");
    }

    public int getToday() {
        Date date = new Date();
        int day = date.getDate();
        int month = date.getMonth();
        int year = date.getYear();
        int result = 10000*(year+1900) + 100*(month+1) + day;
        return result;
    }

}
