package arboretum.arboretumwojslawice.View.fragments;

import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.DividerItemDecoration;
import arboretum.arboretumwojslawice.Commons.WeatherManager;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.adapter.NewsAdapter;
import arboretum.arboretumwojslawice.ViewModel.NewsViewModel;
import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment {

    TextView weatherTemp, weatherDesc;
    ImageView weatherIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        weatherTemp = view.findViewById(R.id.weatherTemperatureTextView);
        weatherDesc = view.findViewById(R.id.weatherDescriptionTextView);
        weatherIcon = view.findViewById(R.id.weatherImageView);

        if (isNetworkConnected()) {
            weatherTemp.setTypeface(null, Typeface.NORMAL);
            weatherIcon.setVisibility(View.VISIBLE);
            weatherDesc.setVisibility(View.VISIBLE);
        } else {
            weatherTemp.setTypeface(null, Typeface.ITALIC);
            weatherTemp.setText(R.string.weather_no_info);
            weatherIcon.setVisibility(View.GONE);
            weatherDesc.setVisibility(View.GONE);
        }



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

    }

    private String getWeatherIconName(String weatherDescription) {
        String weatherString = weatherDescription.toLowerCase();
        weatherString = weatherString.replace(" ", "_");
        weatherString = "weather_" + weatherString;
        return weatherString;
    }

    private int getDrawableId(String drawableName) {
        return getContext().getResources().getIdentifier("arboretum.arboretumwojslawice:drawable/" + drawableName, null, null);
    }

    private String getStringResourceByName(String stringName) {
        int resId = getResources().getIdentifier(stringName, "string", "arboretum.arboretumwojslawice");
        return getString(resId);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(getContext().CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}
