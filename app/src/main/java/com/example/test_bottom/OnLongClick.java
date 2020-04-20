package com.example.test_bottom;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import java.io.File;

import androidx.appcompat.app.AlertDialog;

public class OnLongClick implements View.OnLongClickListener {

  Context context;
  File file;




  public OnLongClick(Context context, File file) {
    this.context = context;
    this.file = file;

  }

  @Override
  public boolean onLongClick(View v) {

    AlertDialog.Builder alertDialog = new AlertDialog.Builder (context);
    alertDialog.setMessage ("DO You Want Delete This File ?");
    alertDialog.setPositiveButton ("Yes", new DialogInterface.OnClickListener () {
      @Override
      public void onClick(DialogInterface dialog, int which) {

        file.delete ();

      }
    });

    alertDialog.setNegativeButton ("No", new DialogInterface.OnClickListener () {
      @Override
      public void onClick(DialogInterface dialog, int which) {



      }
    });

    alertDialog.create ().show ();


    return false;
  }
}
