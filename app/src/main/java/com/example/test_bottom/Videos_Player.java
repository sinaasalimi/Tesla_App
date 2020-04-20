package com.example.test_bottom;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Videos_Player extends AppCompatActivity {

  VideoView video_view;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView (R.layout.videos_player);



    video_view = findViewById (R.id.video_view);
    MediaController mediaController = new MediaController (this);
    mediaController.setAnchorView (video_view);
    video_view.setMediaController (mediaController);

    Intent intent = getIntent ();

    if (intent.hasExtra ("video_path")){

      String path = intent.getStringExtra ("video_path");
      video_view.setVideoPath (path);
      video_view.start ();

    }else{

      String path = intent.getStringExtra ("video_link");
      video_view.setVideoPath (path);
      video_view.start ();



    }















  }
}
