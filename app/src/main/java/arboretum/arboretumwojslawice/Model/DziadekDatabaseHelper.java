package arboretum.arboretumwojslawice.Model;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import arboretum.arboretumwojslawice.R;

public class DziadekDatabaseHelper {

    private static final String DB_NAME = AppDatabase.DATABASE_NAME;

    public void execute(Context context) {
        String databaseFilePath = context.getApplicationInfo().dataDir + "/databases/" + DB_NAME;

        try (InputStream inputStream = context.getResources().openRawResource(R.raw.arboretum_db_from_device)) {
            try (FileOutputStream outputStream = new FileOutputStream(databaseFilePath)) {
                copyTo(inputStream, outputStream);
                outputStream.flush();
                inputStream.close();
                outputStream.close();
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }
    }

    public void copyTo(InputStream input, OutputStream out) throws IOException {
        byte[] buffer = new byte[8 * 1024];
        int bytes = input.read(buffer);
        while (bytes >= 0) {
            out.write(buffer, 0, bytes);
            bytes = input.read(buffer);
        }
    }

}
