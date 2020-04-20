package com.example.test_bottom.itemOfFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.test_bottom.GetJson;
import com.example.test_bottom.R;
import com.example.test_bottom.Cons;

import org.json.JSONArray;
import org.json.JSONException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Home1 extends Fragment {

  RecyclerView rec_home1;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view  = inflater.inflate (R.layout.home1,container,false);
     rec_home1  =view.findViewById (R.id.rec_home1);

    new GetJsonMain (getActivity (),new Cons ().json_main_link).execute ();

    return view;
  }





  public  class GetJsonMain extends GetJson {

    Context context;

    public GetJsonMain(Context context, String jsonUrl) {
      super (context, jsonUrl);

    }



    @Override
    protected void onPostExecute(final String jsonString) {
      super.onPostExecute (jsonString);

      if(!jsonString.equals ("error")) {

        try {
          final JSONArray jsonArray = new JSONArray (jsonString);

          rec_home1.setLayoutManager (new
            LinearLayoutManager (context,RecyclerView.VERTICAL,false));
          rec_home1.setAdapter (new Rec_Home1 (context,jsonArray));

        } catch (JSONException e) {
          e.printStackTrace ();
        }


      }else  {

        Toast.makeText (context,"JsonDownloader", Toast.LENGTH_LONG).show ();
      }
    }
  }



}
