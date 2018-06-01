package arboretum.arboretumwojslawice.View.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Model.businessentity.NewsImage;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.View.adapter.NewsAdapter;
import arboretum.arboretumwojslawice.ViewModel.NewsDetailViewModel;
import arboretum.arboretumwojslawice.databinding.ActivityNewsDetailBinding;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsDetailActivity extends DaggerAppCompatActivity {

    @Inject
    NewsDetailViewModel newsDetailViewModel;
    private CompositeDisposable compositeDisposable;

    TextView titleTextView, descTextView;
    ImageView mainImageView;
    Context context;

    List<NewsImage> newsImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail_grid);
        context = getApplicationContext();
        compositeDisposable = new CompositeDisposable();

        /* toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar_back_news);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.title_home));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        /* /toolbar */

        GridView gridView = findViewById(R.id.gridview);

        Disposable cdNews = Maybe.fromCallable(() -> {
            return newsDetailViewModel.getCurrentNews();
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(news -> {

                    Disposable cdImages = Maybe.fromCallable(() -> {
                        return newsDetailViewModel.getAllImagesById(news.getIdNews());
                    })
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(images -> {

                                if (images.isEmpty()) {
                                    setContentView(R.layout.activity_news_detail);
                                    titleTextView = findViewById(R.id.newsTitleTextView);
                                    descTextView = findViewById(R.id.plant_detail_description);
                                    mainImageView = findViewById(R.id.newsImageView);

                                    titleTextView.setText(news.getName());
                                    descTextView.setText(news.getDescription());
                                    mainImageView.setImageResource(news.getImageId(context));
                                }
                                else {
//                                    setContentView(R.layout.activity_news_detail_grid);
                                    titleTextView = findViewById(R.id.titleTextView);
                                    mainImageView = findViewById(R.id.mainImageView);

                                    titleTextView.setText(news.getName());
                                    mainImageView.setImageResource(news.getImageId(context));

                                    images.add(0, new NewsImage(1000, news.getIdNews(), news.getImage()));
                                    newsImages = images;

//                                    GridView gridView = findViewById(R.id.gridview);
                                    NewsAdapter newsAdapter = new NewsAdapter(this, images);
                                    gridView.setAdapter(newsAdapter);
                                }
                            }
                            ,throwable -> {
                                /* onError() */
                                Toast.makeText(this, "Jakiś błąąąd z obrazkami... -.- -.-", Toast.LENGTH_LONG).show();
                            });
                        }
                        ,throwable -> {
                            /* onError() */
                            Toast.makeText(this, "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG).show();
                        });

        compositeDisposable.add(cdNews);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                if (newsImages.size() > position) {
                    NewsImage newsImage = newsImages.get(position);
                    mainImageView.setImageResource(newsImage.getImageId(context));
                }
            }
        });


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

}
