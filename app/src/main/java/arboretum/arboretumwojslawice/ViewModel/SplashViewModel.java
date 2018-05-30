package arboretum.arboretumwojslawice.ViewModel;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import arboretum.arboretumwojslawice.Model.Repository.AttractionRepository;
import arboretum.arboretumwojslawice.Model.Repository.EventRepository;
import arboretum.arboretumwojslawice.Model.Repository.NewsRepository;
import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;
import arboretum.arboretumwojslawice.Model.additionalEntity.EventRowList;
import arboretum.arboretumwojslawice.Model.businessentity.Event;
import arboretum.arboretumwojslawice.Model.businessentity.News;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;

import java.util.Map;
import java.util.Random;


public class SplashViewModel {

    @Inject
    NewsRepository newsRepository;
    @Inject
    PlantRepository plantRepository;
    @Inject
    EventRepository eventRepository;
    List<String> eventImages;


    @Inject
    public SplashViewModel() {
        eventImages = new ArrayList<>();
        String eventImageName = "event_background_0";
        for (int i=1; i<=7; i++) {
            eventImages.add((eventImageName + String.valueOf(i)));
        }
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

//        int nearestDate = eventRepository.getAllDateFromToday().get(0);
        int nearestDate = eventRepository.getToday();
        if (!eventRepository.getAllDateFromToday().isEmpty())
            nearestDate = eventRepository.getAllDateFromToday().get(0);

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
                eventNames = " " + eventsDuringNearestDate.get(0).getType().toLowerCase();
            }
        }

        return new EventRowList(nearestDate, eventNames);
    }

    private int getStringResource(Context context, String stringName) {
        return context.getResources().getIdentifier(stringName, "string", "arboretum.arboretumwojslawice");
    }



    public String getEventImageName() {
        Random r = new Random();
        int index = r.nextInt(eventImages.size());
        return eventImages.get(index);
    }

    public int getEventImageId(Context c) {
        return c.getResources().getIdentifier("arboretum.arboretumwojslawice:drawable/" + getEventImageName(), null, null);
    }

}
