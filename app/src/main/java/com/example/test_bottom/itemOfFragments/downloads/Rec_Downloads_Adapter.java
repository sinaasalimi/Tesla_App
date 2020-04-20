package com.example.test_bottom.itemOfFragments.downloads;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test_bottom.OnLongClick;
import com.example.test_bottom.R;
import com.example.test_bottom.Cons;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.navigation.Navigation.findNavController;

public class Rec_Downloads_Adapter extends RecyclerView.Adapter<Rec_Downloads_Adapter.ViewHolder> {


  Context context;
  File[] fileIn;


  public Rec_Downloads_Adapter(Context context, File[] fileIn) {

    this.context = context;
    this.fileIn = fileIn;


  }


  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.rec_downloads, parent, false);

    return new ViewHolder (view);
  }

  @Override
  public void onBindViewHolder(@NonNull Rec_Downloads_Adapter.ViewHolder holder, final int position) {


    holder.txt_downloads.setText (fileIn[position].getName ());

    final File file = fileIn[position];

    holder.cons.setOnLongClickListener (new OnLongClick (context, file));

    if (file.isDirectory () && !file.getName ().equals ("Catch")) {

      holder.img_downloads.setBackgroundResource (R.drawable.play);
      holder.cons.setOnClickListener (new View.OnClickListener () {
        @Override
        public void onClick(View v) {

          Bundle bundle = new Bundle ();
          bundle.putString ("videos_path", file.getPath ());


          findNavController ((Activity) context, R.id.nav_host_fragment2)
            .navigate (R.id.action_downloads_to_videos_File, bundle);

        }
      });



    } else {
      holder.img_downloads.setBackgroundResource (R.drawable.pdf);
      holder.cons.setOnClickListener (new View.OnClickListener () {
        @Override
        public void onClick(View v) {

          Intent intent = new Intent (Intent.ACTION_VIEW);
          intent.setDataAndType (Uri.fromFile (file), "application/pdf");
          intent.setFlags (Intent.FLAG_ACTIVITY_NO_HISTORY);
          context.startActivity (intent);

        }
      });


    }


  }


  @Override
  public int getItemCount() {
    return new File (
      new Cons ().external_location_directory).listFiles ().length;
  }


  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView txt_downloads;
    ImageView img_downloads;
    ConstraintLayout cons;


    @SuppressLint("WrongViewCast")
    public ViewHolder(@NonNull View itemView) {
      super (itemView);


      txt_downloads = itemView.findViewById (R.id.txt_downloads);
      img_downloads = itemView.findViewById (R.id.img_downloads);
      cons = itemView.findViewById (R.id.cons);


    }
  }
}
