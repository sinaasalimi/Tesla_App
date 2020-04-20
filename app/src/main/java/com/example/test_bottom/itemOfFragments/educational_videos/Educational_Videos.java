package com.example.test_bottom.itemOfFragments.educational_videos;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Educational_Videos extends Fragment {

  RecyclerView rec1_edu_vid;
  String string =null;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate (R.layout.educational_videos,container,false);


    String s  = getArguments ().getString ("JsonSecPath");
    File file = new File (s);

    try {

      BufferedReader reader = new BufferedReader (new FileReader (file));
      while (!(reader.readLine () == null)) string = string + reader.readLine ();
      JSONObject jsonObject = new JSONObject (string);
      JSONArray jsonArray = jsonObject.getJSONArray ("EducationVideos");

      rec1_edu_vid = view.findViewById (R.id.rec1_edu_vid);
      rec1_edu_vid.setLayoutManager (new LinearLayoutManager (getActivity (), RecyclerView.VERTICAL, false));
      rec1_edu_vid.setAdapter (new Rec1_Edu_Vid_Adapter (getActivity (),jsonArray ));

    } catch (FileNotFoundException e) {
      e.printStackTrace ();
    } catch (IOException e) {
      e.printStackTrace ();
    } catch (JSONException e) {
      e.printStackTrace ();
    }

    return view;
  }

  @Override
  public void onResume() {
    super.onResume ();
  }
}
