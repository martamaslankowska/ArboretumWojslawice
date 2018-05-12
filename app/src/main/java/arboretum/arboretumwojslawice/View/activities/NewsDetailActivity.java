package arboretum.arboretumwojslawice.View.activities;

import android.os.Bundle;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.NewsDetailViewModel;
import dagger.android.support.DaggerAppCompatActivity;

public class NewsDetailActivity extends DaggerAppCompatActivity {

    @Inject
    NewsDetailViewModel newsDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

    }




}
