package arboretum.arboretumwojslawice.Commons;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by weronika on 26.05.2018.
 */

public class DownloadFileFromURL extends AsyncTask<String, String, String> {

    /**
     * Before starting background thread Show Progress Bar Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //showDialog(progress_bar_type);
    }

    /**
     * Downloading file in background thread
     * */
    @Override
    protected String doInBackground(String... f_url) {
        int count;
        try {
            URL url = new URL(f_url[0]);
            URLConnection conection = url.openConnection();
            conection.connect();

            // this will be useful so that you can show a tipical 0-100%
            // progress bar
            int lenghtOfFile = conection.getContentLength();

            // download the file
            InputStream input = new BufferedInputStream(url.openStream(),
                    8192);

            // Output stream
            OutputStream output = new FileOutputStream("/data/data/arboretum.arboretumwojslawice/databases/ArboretumDB");

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();
            Globals.isDownload = true;

        } catch (Exception e) {
            Log.e("Arboretum", "nie pobrało się!");
        }

        return null;
    }

    /**
     * Updating progress bar
     * */
    protected void onProgressUpdate(String... progress) {
        // setting progress percentage
        //pDialog.setProgress(Integer.parseInt(progress[0]));
    }

    /**
     * After completing background task Dismiss the progress dialog
     * **/
    @Override
    protected void onPostExecute(String file_url) {
        // dismiss the dialog after the file was downloaded
        //dismissDialog(progress_bar_type);

    }

}