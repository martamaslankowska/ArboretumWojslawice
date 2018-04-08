package arboretum.arboretumwojslawice.Model;


/* Class being responsible for placing the database file (.db) in right place
*  Based on tutorial: https://blog.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/ */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
//    private static String DB_PATH = "/data/data/arboretum.arboretumwojslawice/databases/";
    private static String DB_PATH = "/data/user/0/arboretum.arboretumwojslawice/databases/";
    private static String DB_NAME = "ArboretumDatabase";

    private SQLiteDatabase arboretumDatabase;
    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     */
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDatabase() throws IOException {

        boolean dbExist = checkDatabase();

        if (dbExist) {
            //do nothing - database already exist
        } else {
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }


        /**
         * Check if the database already exist to avoid re-copying the file each time you open the application.
         * @return true if it exists, false if it doesn't
         */
        private boolean checkDatabase(){
            SQLiteDatabase checkDB = null;

            try{
                String myPath = DB_PATH + DB_NAME;
                checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            } catch(SQLiteException e){
                //database does't exist yet.
            }

            if(checkDB != null){
                checkDB.close();
            }

            return checkDB != null ? true : false;
        }


    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDatabase() throws IOException{

        String databaseName = DB_NAME + ".db";
        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(databaseName);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }


    public void openDatabase() throws SQLException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        arboretumDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if(arboretumDatabase != null)
            arboretumDatabase.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {}

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}
}
