package arboretum.arboretumwojslawice.ViewModel;

import java.util.List;

import javax.inject.Inject;
import arboretum.arboretumwojslawice.Model.Repository.AttractionRepository;
import arboretum.arboretumwojslawice.Model.Repository.EventRepository;
import arboretum.arboretumwojslawice.Model.Repository.NewsRepository;
import arboretum.arboretumwojslawice.Model.Repository.PlantRepository;
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



}
