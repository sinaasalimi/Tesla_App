package com.example.test_bottom.itemOfFragments.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test_bottom.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Library extends Fragment {





  RecyclerView rec1_lib;
   String string;


  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                           final Bundle savedInstanceState) {
    View view = inflater.inflate (R.layout.library,container,false);

    String s  = getArguments ().getString ("JsonSecPath");
    File file = new File (s);

    try {

      BufferedReader reader = new BufferedReader (new FileReader (file));
      while (!(reader.readLine () == null)) string = string + reader.readLine ();
      JSONObject jsonObject = new JSONObject (string);
      JSONArray jsonArray  = jsonObject.getJSONArray ("Library");

      rec1_lib = view.findViewById (R.id.rec1_lib);
      rec1_lib.setLayoutManager (
        new LinearLayoutManager (getActivity (), RecyclerView.VERTICAL,false));
      rec1_lib.setAdapter (new Rec1_Lib_Adapter (getContext (),jsonArray));

    } catch (FileNotFoundException e) {
      e.printStackTrace ();
    } catch (IOException e) {
      e.printStackTrace ();
    } catch (JSONException e) {
      e.printStackTrace ();
    }

    return view;

  }




}
