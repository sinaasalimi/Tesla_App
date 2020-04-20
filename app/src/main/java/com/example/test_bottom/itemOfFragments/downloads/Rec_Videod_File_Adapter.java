package com.example.test_bottom.itemOfFragments.downloads;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.test_bottom.OnLongClick;
import com.example.test_bottom.R;
import com.example.test_bottom.Videos_Player;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class Rec_Videod_File_Adapter extends RecyclerView.Adapter<Rec_Videod_File_Adapter.ViewHolder> {

  Context context;
  File[] files;

  public Rec_Videod_File_Adapter(Context context, File file) {

    this.context = context;
    this.files = file.listFiles ();


  }

  @NonNull
  @Override
  public Rec_Videod_File_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.rec_videos_files, parent, false);


    return new ViewHolder (view);
  }

  @Override
  public void onBindViewHolder(@NonNull Rec_Videod_File_Adapter.ViewHolder holder, int position) {

    final File file = files[position];

    holder.txt_rec_videos_files.setText (file.getName ());
    holder.cons_rec_video_files.setOnLongClickListener (new OnLongClick (context,file));
    holder.cons_rec_video_files.setOnClickListener (new View.OnClickListener () {
      @Override
      public void onClick(View v) {

        context.startActivity (new Intent (context, Videos_Player.class)
          .putExtra ("video_path", file.getPath ()));
      }
    });


  }

  @Override
  public int getItemCount() {
    return files.length;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView txt_rec_videos_files;
    ConstraintLayout cons_rec_video_files;


    public ViewHolder(@NonNull View itemView) {
      super (itemView);

      txt_rec_videos_files = itemView.findViewById (R.id.txt_rec_videse_files);
      cons_rec_video_files = itemView.findViewById (R.id.cons_rec_video_files);


    }
  }
}
