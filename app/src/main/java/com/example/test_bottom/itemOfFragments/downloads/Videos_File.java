package com.example.test_bottom.itemOfFragments.downloads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test_bottom.R;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Videos_File extends Fragment {

  RecyclerView rec_videos_files;



  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View view  = inflater.inflate (R.layout.videos_file,container,false);
    File file = new File (getArguments ().getString ("videos_path"));

    rec_videos_files = view.findViewById (R.id.rec_videos);
    rec_videos_files.setLayoutManager (new LinearLayoutManager (getActivity (),RecyclerView.VERTICAL,false));
    rec_videos_files.setAdapter (new Rec_Videod_File_Adapter (getActivity (),file));





    return view;
  }




}
