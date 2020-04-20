package com.example.test_bottom.itemOfFragments.library;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test_bottom.Connection;
import com.example.test_bottom.R;
import com.example.test_bottom.Cons;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class Rec2_Lib_Adapter extends RecyclerView.Adapter<Rec2_Lib_Adapter.RecViewHolder>{

  JSONArray jsonArray;
  Context context;
  Intent intent;

  public Rec2_Lib_Adapter(JSONArray jsonArray, Context context){
    this.jsonArray = jsonArray;
    this.context = context;
  }



  @NonNull
  @Override
  public Rec2_Lib_Adapter.RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.rec2_library,parent,false);

    return new Rec2_Lib_Adapter.RecViewHolder (view);
  }

  @Override
  public void onBindViewHolder(final RecViewHolder holder, final int position) {

    try {

      final JSONObject jsonObject = jsonArray.getJSONObject (position);
      String pdfName = jsonObject.getString ("Name");

      final File file = new File (
        new Cons ().external_location_directory+ pdfName + ".pdf");
      holder.txt_book_name_top5.setText (pdfName);

      Picasso.get ().load (jsonObject.getString ("Image")).resize (141,196).into (holder.img_book_top5);

      if(file.exists ()){holder.btn_top5.setText ("Open");}else {holder.btn_top5.setText ("Download");}

      holder.btn_top5.setOnClickListener (new View.OnClickListener () {
        @Override
        public void onClick(View v) {
          try {

            if (holder.btn_top5.getText ().equals ("Open")){

              intent = new Intent(Intent.ACTION_VIEW);
              intent.setDataAndType(Uri.fromFile(file), "application/pdf");
              intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
              context.startActivity(intent);

            }else {

                new Connection.DownloadTask (context,jsonObject.getString ("Name"),"pdf")
                  .execute (jsonObject.getString ("PDF"));

            }
          } catch (JSONException e) {
            e.printStackTrace ();
          }
        }
      });
    } catch (JSONException e) {
      e.printStackTrace ();
    }
  }


  @Override
  public int getItemCount() {

      return jsonArray.length ();
  }

  public class RecViewHolder extends RecyclerView.ViewHolder {

    TextView txt_book_name_top5;
    ImageView img_book_top5;
    Button btn_top5;

    public RecViewHolder( View itemView) {
      super (itemView);

      txt_book_name_top5 = itemView.findViewById (R.id.txt_book_name_top5);
      img_book_top5 = itemView.findViewById (R.id.img_book_top5);
      btn_top5 = itemView.findViewById (R.id.btn_top5);

    }
  }





  /*private class DownloadTask extends AsyncTask<String, Integer, String> {

    private Context context;
    ProgressDialog mProgressDialog;
    String pdfName;
    public DownloadTask(Context context,ProgressDialog mProgressDialog , String pdfName) {

      this.context = context;
      this.mProgressDialog = mProgressDialog;
      this.pdfName = pdfName;
    }


    @SuppressLint("WakelockTimeout")
    @Override
    protected String doInBackground(String... sUrl) {

      PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
      PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,getClass().getName());
      wl.acquire();

      try {
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        try {
          URL url = new URL(sUrl[0]);
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
          output = new FileOutputStream ("/sdcard/Tesla/" + pdfName);  //   /sdcard/file_name.extension

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
        Toast.makeText(context,"File downloaded", Toast.LENGTH_SHORT).show();

      File file = new File ("/sdcard/Tesla/" + pdfName);
      intent = new Intent(Intent.ACTION_VIEW);
      intent.setDataAndType(Uri.fromFile(file), "application/pdf");
      intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
      context.startActivity(intent);
    }

  }*/


 /* private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {

      this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
      String urldisplay = urls[0];
      Bitmap mIcon11 = null;
      try {
        InputStream in = new java.net.URL(urldisplay).openStream();
        mIcon11 = BitmapFactory.decodeStream(in);
      } catch (Exception e) {
        Log.e("Error", e.getMessage());
        e.printStackTrace();
      }
      return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
      bmImage.setImageBitmap(result);
    }
  }*/
}
