package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import javax.inject.Inject;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
//        ActivityNewsDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail);
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


        Disposable cdNews = Maybe.fromCallable(() -> {
            return newsDetailViewModel.getCurrentNews();
        })
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(news -> {
//                            binding.setNews(news);


                    Disposable cdImages = Maybe.fromCallable(() -> {
                        return newsDetailViewModel.getAllImagesById(news.getIdNews());
                    })
                            .subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(images -> {

                                if (images.isEmpty())
                                    setContentView(R.layout.activity_news_detail);
                                else {
                                    setContentView(R.layout.activity_news_detail_grid);

                                    GridView gridView = findViewById(R.id.gridview);
                                    NewsAdapter newsAdapter = new NewsAdapter(this, images);
                                    gridView.setAdapter(newsAdapter);
                                }


                                        int length = images.size();
                                        Toast.makeText(this, String.valueOf(length), Toast.LENGTH_SHORT).show();
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
