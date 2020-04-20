package com.example.test_bottom.itemOfFragments.downloads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test_bottom.R;
import com.example.test_bottom.Cons;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Downloads extends Fragment {


  RecyclerView rec_mybook;

  Rec_Downloads_Adapter rec_downloads_adapter;




  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate (R.layout.downloads,container,false);


    File[] files =  new File(new Cons ().external_location_directory).listFiles ();

    Rec_Downloads_Adapter rec_downloads_adapter = new Rec_Downloads_Adapter (getActivity (),files);
    this.rec_downloads_adapter = rec_downloads_adapter;

    rec_mybook = view.findViewById (R.id.rec_mybook);
    rec_mybook.setLayoutManager (new LinearLayoutManager (getActivity (), LinearLayoutManager.VERTICAL,false));
    rec_mybook.setAdapter (rec_downloads_adapter);




    return view;
  }



}

