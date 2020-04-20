package com.example.test_bottom;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public  class GetJson extends AsyncTask<Void,Void,String> {


  Context context;
  String jsonUrl  ;
  ProgressDialog progressDialog;

  public GetJson(Context context,String jsonUrl){
    this.context = context;
    this.jsonUrl = jsonUrl;
    progressDialog = new ProgressDialog (this.context);
  }

  @Override
  protected String doInBackground(Void... voids) {
    return getJson (jsonUrl);
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute ();

    progressDialog.setMessage ("loading Items");
    progressDialog.setCancelable (false);
    progressDialog.show ();

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
      return "error";

    }

  }

}
