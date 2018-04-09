package arboretum.arboretumwojslawice.View.activities;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import arboretum.arboretumwojslawice.Model.AppDatabase;
import arboretum.arboretumwojslawice.Model.DAO.AttractionDao;
import arboretum.arboretumwojslawice.Model.DatabaseConnection;
import arboretum.arboretumwojslawice.Model.DatabaseHelper;
import arboretum.arboretumwojslawice.Model.Entity.PriceEntity;
import arboretum.arboretumwojslawice.Model.Entity.PriceTranslationEntity;
import arboretum.arboretumwojslawice.Model.businessentity.Price;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /* COPYING DATABASE */

//        DatabaseHelper myDbHelper = new DatabaseHelper(this);
//
//        try {
//            myDbHelper.createDatabase();
//        } catch (IOException ioe) {
//            throw new Error("Unable to create Database");
//        }
//
//        try {
//            myDbHelper.openDatabase();
//        } catch(SQLException sqle){
//            throw sqle;
//        }

//        /* Checking if table exists in the database */
//        boolean exists = tableExists((DatabaseHelper.DB_NAME + DatabaseHelper.DB_EX));

        /* FINISHED COPING DATABASE */

        AppDatabase database = AppDatabase.getAppDatabase(getApplicationContext());

//        /* INSERTING TEST DATA */
//        Price p1 = new Price(1, 30.0, "normalny", "bilet dla normalnych ludzi");
//        Price p2 = new Price(2, 10.0, "ulgowy", "bilet tylko za pokazaniem ważnego dokumentu potwierdzającego przysługujące ulgi");
//        Price p3 = new Price(3, 15.0, "inny", "a jakiśtam inny, trzeci bilet");
//        String translationCode = this.getResources().getConfiguration().locale.getLanguage();


        DatabaseConnection dbConnect = new DatabaseConnection(getApplicationContext(), database);
        dbConnect.execute();


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean tableExists(String databaseName) {
        Cursor c = null;
        SQLiteDatabase mDatabase = openOrCreateDatabase(databaseName, MODE_PRIVATE,null);
        boolean tableExists = false;

        /* get cursor on it */
        try {
            c = mDatabase.query("Prices", null,null, null, null, null, null);
            int nrOfRows = c.getCount();
            tableExists = true;
        } catch (Exception e) {
            /* fail */
            Log.d("ERROR", "Table doesn't exist :(");
        }

        return tableExists;
    }


}



