package com.example.test_bottom.itemOfFragments.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test_bottom.R;

import org.json.JSONArray;
import org.json.JSONException;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Section extends Fragment {




  RecyclerView rec_sec;


  @Nullable
  @Override
  public View onCreateView( LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

    View view = inflater.inflate (R.layout.section,container,false);

    String jsonString = getArguments ().getString ("jsonArray");

    try {

      JSONArray jsonArray = new JSONArray (jsonString);

      rec_sec = view.findViewById (R.id.rec_sec);
      rec_sec.setLayoutManager (new LinearLayoutManager (getActivity (), RecyclerView.VERTICAL,false));
      rec_sec.setAdapter (new Rec_Sec_Adapter (getActivity (),jsonArray));

    } catch (JSONException e) {
      e.printStackTrace ();
    }



    return view;
  }
}
