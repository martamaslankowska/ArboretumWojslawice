package arboretum.arboretumwojslawice.View.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import arboretum.arboretumwojslawice.Model.DatabaseHelper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /* COPING DATABASE */

        DatabaseHelper myDbHelper = new DatabaseHelper(this);

        try {
            myDbHelper.createDatabase();
        } catch (IOException ioe) {
            Toast.makeText(this, "Create database - failed", Toast.LENGTH_LONG).show();
            throw new Error("Unable to create Database");
        }

        try {
            myDbHelper.openDatabase();
        } catch(SQLException sqle){
            Toast.makeText(this, "Open database - failed", Toast.LENGTH_LONG).show();
            throw sqle;
        }

        /* Checking if table exists in the database */
        boolean exists = tableExists("ArboretumDB");

        /* FINISHED COPING DATABASE */


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean tableExists(String databaseName) {
        Cursor c = null;
        SQLiteDatabase mDatabase = openOrCreateDatabase((databaseName + ".db"), MODE_PRIVATE,null);
        boolean tableExists = false;

        /* get cursor on it */
        try {
            c = mDatabase.query("Prices", null,null, null, null, null, null);
            tableExists = true;
        } catch (Exception e) {
            /* fail */
            Log.d("ERROR", "Table doesn't exist :(");
        }

        return tableExists;
    }


}
