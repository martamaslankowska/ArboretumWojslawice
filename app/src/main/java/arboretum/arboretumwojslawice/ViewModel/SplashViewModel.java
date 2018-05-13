package arboretum.arboretumwojslawice.ViewModel;

import android.content.Context;

import java.util.ArrayList;
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

import java.util.Random;


public class SplashViewModel {

    @Inject
    NewsRepository newsRepository;
    @Inject
    PlantRepository plantRepository;
    @Inject
    EventRepository eventRepository;

    @Inject
    SplashViewModel() {}


    public News getCurrentNews() {
        return newsRepository.getAllPastNews().get(0);
    }

    public Plant getRandomSeassonPlant() {
        int today = plantRepository.getToday();
        List<Plant> plants = plantRepository.getAllSeasonPlants(today);
        Random r = new Random();
        int index = r.nextInt(plants.size());
        return plants.get(index);
    }

    public EventRowList getEventNameConcatenate(Context context) {
        String eventNames = "";

        int nearestDate = eventRepository.getAllDateFromToday().get(0);
        List<Event> eventsDuringNearestDate = eventRepository.getAllDuringGivenDate(nearestDate);
        int nrOfEvents = eventsDuringNearestDate.size();

        if (nrOfEvents > 0) {
            if (nrOfEvents > 1) {
                for (int i = 0; i < nrOfEvents; i++) {
                    if (i != 0)
                        eventNames += " ";
                    eventNames += eventsDuringNearestDate.get(i).getType().toLowerCase();

                    if (i == (nrOfEvents - 1))
                        eventNames += " " + context.getResources().getString(getStringResource(context, "ampersand"));
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

}
