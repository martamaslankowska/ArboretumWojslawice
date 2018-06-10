package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.Globals;
import arboretum.arboretumwojslawice.Model.businessentity.Hotel;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.ContactViewModel;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ContactActivity extends DaggerAppCompatActivity {

    TextView mainPhoneTextView;
    CompositeDisposable compositeDisposable;

    @Inject
    protected ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        /* Not working :( */
//        mainPhoneTextView = findViewById(R.id.mainPhoneText);
//        mainPhoneTextView.setAutoLinkMask(Linkify.PHONE_NUMBERS);


        /* toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_contact);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        /* /toolbar */

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
    }

    public void onNavigationButtonClick(View view){
        String uri = "http://maps.google.com/maps?saddr=" + 51.107215 + "," + 17.033463 + "&daddr=" + 50.712593+ "," + 16.856435;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

}
