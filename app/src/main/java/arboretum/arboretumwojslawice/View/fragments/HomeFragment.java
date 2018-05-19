package arboretum.arboretumwojslawice.View.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.Globals;
import arboretum.arboretumwojslawice.Commons.WeatherManager;
import arboretum.arboretumwojslawice.Model.additionalEntity.EventRowList;
import arboretum.arboretumwojslawice.Model.businessentity.News;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.NewsViewModel;
import dagger.android.support.DaggerFragment;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

public class HomeFragment extends DaggerFragment {

    TextView weatherTemp, weatherDesc;
    ImageView weatherIcon;
    TextView newsTitle, newsText, eventTitle, eventText, plantTitle, plantText;
    ImageView newsImage, eventImage, plantImage;

    Context context;
    CompositeDisposable compositeDisposable;
    @Inject
    NewsViewModel newsViewModel;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        eventImage = view.findViewById(R.id.eventImageView);
        eventImage.setImageResource(Globals.eventImageResource);

        /* SETTING THE WEATHER */

        weatherTemp = view.findViewById(R.id.weatherTemperatureTextView);
        weatherDesc = view.findViewById(R.id.weatherDescriptionTextView);
        weatherIcon = view.findViewById(R.id.weatherImageView);

        if (isNetworkConnected() && isInternetOn()) {
            weatherTemp.setTypeface(null, Typeface.NORMAL);
            weatherIcon.setVisibility(View.VISIBLE);
            weatherDesc.setVisibility(View.VISIBLE);

            WeatherManager.placeIdTask asyncTask = new WeatherManager.placeIdTask(new WeatherManager.AsyncResponse() {
                public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn) {
                    String weatherName = getWeatherIconName(weather_description);

                    weatherTemp.setTextSize(32);
                    weatherTemp.setText(weather_temperature.replace(",00", ""));
                    weatherDesc.setText(getStringResourceByName(weatherName));
                    weatherIcon.setImageResource(getDrawableId(weatherName));
                }
            });
            asyncTask.execute("50.7163", "16.835");  // Niemcza - namiary ze strony openweathermap
        } else {
            weatherTemp.setTypeface(null, Typeface.ITALIC);
            weatherTemp.setText(R.string.weather_no_info);
            weatherIcon.setVisibility(View.GONE);
            weatherDesc.setVisibility(View.GONE);
        }


        /* SETTING NEWS */

        newsTitle = view.findViewById(R.id.newsTitleTextView);
        newsText = view.findViewById(R.id.newsInfoTextView);
        newsImage = view.findViewById(R.id.newsImageView);

        if (Globals.currentNews == null) {
            Disposable cdNews = Maybe.fromCallable(() -> {
                return newsViewModel.getCurrentNews();
            })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(news -> {
                                Globals.currentNews = news;
                                newsTitle.setText(news.getName());
                                newsText.setText(news.getDescription());
                                newsImage.setImageResource(news.getImageId(context));
                            }
                            , throwable -> {
                                Toast.makeText(context, "Oh no! Something went terribly wrong with news", Toast.LENGTH_LONG);
                            });
            compositeDisposable.add(cdNews);
        } else {
            News news = Globals.currentNews;
            newsTitle.setText(news.getName());
            newsText.setText(news.getDescription());
            newsImage.setImageResource(news.getImageId(context));
        }


        /* SETTING EVENTS */

        eventTitle = view.findViewById(R.id.eventTitleTextView);
        eventText = view.findViewById(R.id.eventInfoTextView);

        if (Globals.nearestEvents == null) {
            Disposable cdEvent = Maybe.fromCallable(() -> {
                return newsViewModel.getEventNameConcatenate(context);
            })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(eventRowList -> {
                                Globals.nearestEvents = eventRowList;
                                String eventTitleText;
                                if (newsViewModel.getToday() == eventRowList.getEventDateInteger())
                                    eventTitleText = context.getString(R.string.event_today);
                                else
                                    eventTitleText = getStringResourceByName("event_title_text") + " " + eventRowList.getEventDateString() + "...";
                                eventTitle.setText(eventTitleText);
                                String eventInfoText = getStringResourceByName("event_info_text_1") + eventRowList.getEventNames() + ".\n" + getStringResourceByName("event_info_text_2");
                                eventText.setText(eventInfoText);
                            }
                            , throwable -> {
                                Toast.makeText(context, "Oh no! Something went terribly wrong with events", Toast.LENGTH_LONG);
                            });
            compositeDisposable.add(cdEvent);
        } else {
            EventRowList eventRowList = Globals.nearestEvents;
            String eventTitleText;
            if (newsViewModel.getToday() == eventRowList.getEventDateInteger())
                eventTitleText = context.getString(R.string.event_today);
            else
                eventTitleText = getStringResourceByName("event_title_text") + " " + eventRowList.getEventDateString() + "...";
            eventTitle.setText(eventTitleText);
            String eventInfoText = getStringResourceByName("event_info_text_1") + eventRowList.getEventNames() + ".\n" + getStringResourceByName("event_info_text_2");
            eventText.setText(eventInfoText);
        }

        /* SETTING SEASSON PLANT */

        plantTitle = view.findViewById(R.id.plantTitleTextView);
        plantText = view.findViewById(R.id.plantInfoTextView);
        plantImage = view.findViewById(R.id.plantImageView);

        if (Globals.seasonPlant == null) {
            Disposable cdPlant = Maybe.fromCallable(() -> {
                return newsViewModel.getRandomSeasonPlant();
            })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(plant -> {
                                Globals.seasonPlant = plant;
                                plantImage.setImageResource(plant.getImageId(context));
                                plantTitle.setText(plant.getName());
                                String plantGenusSpeciesText = plant.getGenusName() + " " + plant.getSpeciesName();
                                plantText.setText(plantGenusSpeciesText);
                            }
                            , throwable -> {
                                Toast.makeText(context, "Oh no! Something went terribly wrong with plants", Toast.LENGTH_LONG);
                            });
            compositeDisposable.add(cdPlant);
        } else {
            Plant plant = Globals.seasonPlant;
            plantImage.setImageResource(plant.getImageId(context));
            plantTitle.setText(plant.getName());
            String plantGenusSpeciesText = plant.getGenusName() + " " + plant.getSpeciesName();
            plantText.setText(plantGenusSpeciesText);
        }

    }


    public void newsClick(View view) { }
    public void eventClick(View view) { }
    public void plantClick(View view) { }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
    }

    private String getWeatherIconName(String weatherDescription) {
        String weatherString = weatherDescription.toLowerCase();

        if (weatherString.indexOf("thunderstorm") >= 0) {
            weatherString = "thunderstorm";
        } else if (weatherString.indexOf("drizzle") >= 0 || weatherString.equals("shower rain")) {
            weatherString = "drizzle";
        } else if (weatherString.indexOf("snow") >= 0 || weatherString.indexOf("sleet") >= 0) {
            weatherString = "snow";
        } else if (weatherString.indexOf("rain") >= 0) {
            weatherString = "rain";
        } else if (weatherString.indexOf("clouds") >= 0) {
            if (weatherString.equals("overcast clouds"))
                weatherString = "broken clouds";
        } else if (weatherString.equals("clear sky")) {
            weatherString = weatherString;
        } else {
            weatherString = "mist";
        }

        weatherString = weatherString.replace(" ", "_");
        weatherString = "weather_" + weatherString;
        return weatherString;
    }

    private int getDrawableId(String drawableName) {
        return context.getResources().getIdentifier("arboretum.arboretumwojslawice:drawable/" + drawableName, null, null);
    }

    private String getStringResourceByName(String stringName) {
        int resId = context.getResources().getIdentifier(stringName, "string", "arboretum.arboretumwojslawice");
        return context.getString(resId);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(getContext().CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public boolean isInternetOn() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            Log.v(TAG, "Internet is working");
            // txt_status.setText("Internet is working");
            return true;
        } else {
            // txt_status.setText("Internet Connection Not Present");
            Log.v(TAG, "Internet Connection Not Present");
            return false;
        }
    }

}
