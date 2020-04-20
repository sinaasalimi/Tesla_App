package com.example.test_bottom;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Splash extends AppCompatActivity {


  Thread thread = new Thread (new Runnable () {
    @Override
    public void run() {

      try {
        Thread.sleep (2000);

        Intent intent = new Intent (Splash.this, MainActivity.class);

        startActivity (intent);
        finish ();

      } catch (InterruptedException e) {

        e.printStackTrace ();

      }


    }
  });




  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.splash);





    if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
      != PackageManager.PERMISSION_GRANTED){

      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},0);

    }else {

      thread.start ();

    }





     // thread.start ();

    }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    switch (requestCode) {

      case 0 :

        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

          thread.start ();

      }else {

          this.finish ();

        }
    }
  }
}



