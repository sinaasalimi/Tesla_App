package com.example.test_bottom.itemOfFragments.educational_videos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.test_bottom.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.navigation.Navigation.findNavController;

class Rec1_Edu_Vid_Adapter extends RecyclerView.Adapter<Rec1_Edu_Vid_Adapter.ViewHolder> {

  Context context;
  JSONArray jsonArray;

  public Rec1_Edu_Vid_Adapter(Context context, JSONArray jsonArray) {
    this.context = context;
    this.jsonArray = jsonArray;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.rec1_edu_vid,parent,false);

    return new ViewHolder (view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    try {
      final JSONObject jsonObject = jsonArray.getJSONObject (position);
      holder.txt_section_name_edu_vid.setText (jsonObject.getString ("SectionName"));
      holder.rec2_edu_vid.setLayoutManager (new LinearLayoutManager (context,RecyclerView.HORIZONTAL,false));
      holder.rec2_edu_vid.setAdapter (new Rec2_Edu_Vid_Adapter(context,jsonObject.getJSONArray ("Top5")));
      holder.btn_rec1_edu_vid.setOnClickListener (new View.OnClickListener () {
        @Override
        public void onClick(View v) {
          Bundle bundle = null;
          try {
            bundle.putString ("JsonArray",jsonObject.getJSONArray ("All").toString ());
            findNavController((Activity) context,R.id.nav_host_fragment2).navigate (R.id.action_edu_vid_to_all_Videos,bundle);
          } catch (JSONException e) {
            e.printStackTrace ();
          }


        }
      });


    } catch (JSONException e) {
      e.printStackTrace ();
    }


  }

  @Override
  public int getItemCount() { return jsonArray.length ();}

  public class ViewHolder extends RecyclerView.ViewHolder {

    RecyclerView rec2_edu_vid;
    TextView txt_section_name_edu_vid;
    Button btn_rec1_edu_vid;


    public ViewHolder(@NonNull View itemView) {
      super (itemView);

      rec2_edu_vid = itemView.findViewById (R.id.rec2_edu_vid);
      btn_rec1_edu_vid = itemView.findViewById (R.id.btn_rec1_edu_vid);
      txt_section_name_edu_vid = itemView.findViewById (R.id.txt_section_name_edu_vid);
    }
  }
}
