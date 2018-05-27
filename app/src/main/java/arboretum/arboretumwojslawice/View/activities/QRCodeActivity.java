package arboretum.arboretumwojslawice.View.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import arboretum.arboretumwojslawice.Commons.Optional;
import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;
import arboretum.arboretumwojslawice.ViewModel.QRCodeViewModel;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class QRCodeActivity extends DaggerAppCompatActivity {

    @Inject
    QRCodeViewModel qrCodeViewModel;

    PublishSubject<Integer> subject;
    Disposable cdPlant;

    SurfaceView cameraPreview;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    final int requestCameraPermissionID = 1001;
    boolean firstLoop = true;

    private CompositeDisposable compositeDisposable;

    public static final String BUNDLE = "BUNDLE";
    public static final String PLANT_ID = "PLANT_ID";
    int plantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        /* toolbar */
        Toolbar toolbar = findViewById(R.id.toolbar_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toolbar_qr_code);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        /* /toolbar */

        subject = PublishSubject.create();

        cameraPreview = findViewById(R.id.cameraSurfaceView);

        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource = new CameraSource.Builder(this, barcodeDetector).build();

        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //Request permission
                    ActivityCompat.requestPermissions(QRCodeActivity.this, new String[]{Manifest.permission.CAMERA}, requestCameraPermissionID);
                    return;
                }
                try {
                    cameraSource.start(cameraPreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });

        compositeDisposable = new CompositeDisposable();
        Disposable cdPlant = subject.throttleFirst(3, TimeUnit.SECONDS)
                .flatMapSingle(value -> Single.fromCallable(() -> {
                    Plant plant = qrCodeViewModel.getById(value);
                    return plant == null ? Optional.empty() : Optional.of(plant);
                })).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dbPlant -> {

                            if (dbPlant.isPresent()) {
                                Intent intent = new Intent(QRCodeActivity.this, PlantDetailActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt(PLANT_ID, plantId);
                                intent.putExtra(BUNDLE, bundle);
                                //Toast.makeText(getApplicationContext(), "Roslina jest w bazie", Toast.LENGTH_LONG).show();
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                //Toast.makeText(getApplicationContext(), "Roslina nie istnieje. Spróbuj jeszcze raz", Toast.LENGTH_LONG).show();
                                Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                                vibrator.vibrate(200);
                            }
                        });
        compositeDisposable.add(cdPlant);

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> QRcodes = detections.getDetectedItems();

                if (QRcodes.size() != 0) {
                    firstLoop = false;
                    String qrResult = QRcodes.valueAt(0).displayValue;
                    plantId = qrResult.length() < 22 ? -1 : Integer.valueOf(qrResult.substring(20));
                    subject.onNext(plantId);
//                    Disposable cdPlant = Maybe.fromCallable(() -> {
//                        return qrCodeViewModel.getById(plantId);
//                    })
//                            .subscribeOn(Schedulers.computation())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(dbPlant -> {
//
//                                        Intent intent = new Intent(QRCodeActivity.this, PlantDetailActivity.class);
//                                        Bundle bundle = new Bundle();
//                                        bundle.putInt(PLANT_ID, plantId);
//                                        intent.putExtra(BUNDLE, bundle);
//                                        Toast.makeText(getApplicationContext(), "Roslina jest w bazie", Toast.LENGTH_LONG).show();
//                                        startActivity(intent);
//                                        finish();
//
//                                    }
//                                    ,onError -> {
//                                        /* onError() */
//                                        Toast.makeText(getApplicationContext(), "Jakiś błąąąd... -.- -.-", Toast.LENGTH_LONG).show();
//                                    }
//                                    ,() -> {
//
//                                        Toast.makeText(getApplicationContext(), "Roslina nie istnieje. Spróbuj jeszcze raz", Toast.LENGTH_LONG).show();
//                                        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
//                                        vibrator.vibrate(200);
//                                        firstLoop = true;
//                                        subject.onNext(plantId)
//
//                                    });


//
//                    Intent intent = new Intent(QRCodeActivity.this, PlantDetailActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putInt(PLANT_ID, plantId);
//                    intent.putExtra(BUNDLE, bundle);
//                    startActivity(intent);
//                    finish();
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case requestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    try {
                        cameraSource.start(cameraPreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}
