
package com.example.test_bottom;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.PowerManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.annotation.RequiresApi;

public class Connection{




  @SuppressLint("StaticFieldLeak")
  public static class DownloadTask extends AsyncTask<String, Integer, String> {

    Context context;
    String  fileName , fileFormat;
    String fileLocation = new Cons ().external_location_directory;
    ProgressDialog mProgressDialog;

    public DownloadTask(Context context,String fileName , String fileFormat) {

      this.context = context;
      this.fileName = fileName + "/";
      this.fileFormat = "."+ fileFormat;

       mProgressDialog = new ProgressDialog (context);
    }


    @SuppressLint("WakelockTimeout")
    @Override
    protected String doInBackground(String... downloadLink) {


      mProgressDialog.setMessage ("Downloading PDF");
      mProgressDialog.setIndeterminate (true);
      mProgressDialog.setProgressStyle (ProgressDialog.STYLE_HORIZONTAL);

      PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
      PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,getClass().getName());
      wl.acquire();

      try {
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        try {
          URL url = new URL(downloadLink[0]);
          connection = (HttpURLConnection) url.openConnection();
          connection.connect();

          // expect HTTP 200 OK, so we don't mistakenly save error report
          // instead of the file
          if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
            return "Server returned HTTP " + connection.getResponseCode()
              + " " + connection.getResponseMessage();

          // this will be useful to display download percentage
          // might be -1: server did not report the length
          int fileLength = connection.getContentLength();

          // download the file
          input = connection.getInputStream();
          if (!new File (fileLocation).exists ()) new File (fileLocation).mkdir ();
          output = new FileOutputStream (fileLocation + fileName + fileFormat );  //   /sdcard/file_name.extension

          byte[] data = new byte[4096];
          long total = 0;
          int count;
          while ((count = input.read(data)) != -1) {
            // allow canceling with back button
            if (isCancelled())
              return null;
            total += count;
            // publishing the progress....
            if (fileLength > 0) // only if total length is known
              publishProgress((int) (total * 100 / fileLength));
            output.write(data, 0, count);
          }
        } catch (Exception e) {
          return e.toString();
        } finally {
          try {
            if (output != null)
              output.close();
            if (input != null)
              input.close();
          }
          catch (IOException ignored) { }

          if (connection != null)
            connection.disconnect();
        }
      } finally {
        wl.release();
      }
      return null;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      mProgressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
      super.onProgressUpdate(progress);
      // if we get here, length is known, now set indeterminate to false
      mProgressDialog.setIndeterminate(false);
      mProgressDialog.setMax(100);
      mProgressDialog.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
      mProgressDialog.dismiss();
      if (result != null)
        Toast.makeText(context,"Download error: "+result, Toast.LENGTH_LONG).show();
      else
        Toast.makeText(context,"File downloaded", Toast.LENGTH_LONG).show();

    }

  }






   public String getJson(String jsonUrl){

     String line = "";

     try {
      URL url = new URL (jsonUrl);
       HttpURLConnection huc = (HttpURLConnection) url.openConnection ();
       huc.connect ();

       InputStream inputStream = huc.getInputStream ();
       BufferedReader br = new BufferedReader (new InputStreamReader (inputStream));
       StringBuffer stringBuffer = new StringBuffer ();

       while ((line = br.readLine ()) != null) {

         stringBuffer.append (line+"\n");

       }

      // br.close ();
       return stringBuffer.toString ();

     } catch (Exception e) {
       e.printStackTrace ();
       return "erorr";

     }

   }





   @RequiresApi(api = Build.VERSION_CODES.M)
   public boolean hasConnect(Context context) {

     ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
     NetworkInfo networkInfo =  connectivityManager.getActiveNetworkInfo ();

     if(networkInfo!=null){

       return true;
     }else {

       return  false;
     }

   }







}