package com.example.test_bottom.itemOfFragments.library;

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


public class Rec1_Lib_Adapter extends RecyclerView.Adapter<Rec1_Lib_Adapter.ViewHolder> {

  JSONArray jsonArray ;
  Context context ;

  public Rec1_Lib_Adapter(Context context, JSONArray jsonArray){

    this.context = context;
    this.jsonArray =jsonArray;

  }


  @NonNull
  @Override
  public Rec1_Lib_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.rec1_library,parent,false);

    return new ViewHolder (view);
  }

  @Override
  public void onBindViewHolder(@NonNull Rec1_Lib_Adapter.ViewHolder holder, final int position) {

    try {

      final JSONObject jsonObject =jsonArray.getJSONObject (position);
      holder.txt_section_name_lib.setText (jsonObject.getString ("SectionName"));
      holder.btn_lib_more.setOnClickListener (new View.OnClickListener () {
        @Override
        public void onClick(View v) {
          try {

            //Casting JsonArray(Section2) To String For Send To Sectoin2.class
            String jsonPutString = jsonObject.getJSONArray ("All").toString ();
            Bundle bundle = new Bundle ();
            bundle.putString ("jsonArray",jsonPutString);
            findNavController((Activity) context,R.id.nav_host_fragment2).navigate (R.id.action_library_to_section,bundle);

          } catch (JSONException e) {
            e.printStackTrace ();
          }}});

      holder.rec2_lib.setLayoutManager (
        new LinearLayoutManager (context, RecyclerView.HORIZONTAL,false));
      holder.rec2_lib.setAdapter (
        new Rec2_Lib_Adapter (jsonObject.getJSONArray ("Top5"),context));

    } catch (JSONException e) {
      e.printStackTrace ();
    }


  }

  @Override
  public int getItemCount() {

    return jsonArray.length ();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    RecyclerView rec2_lib;
    TextView txt_section_name_lib;
    Button btn_lib_more;


    public ViewHolder( View itemView) {
      super (itemView);


      rec2_lib = itemView.findViewById (R.id.rec2_edu_vid);
      txt_section_name_lib = itemView.findViewById (R.id.txt_section_name_edu_vid);
      btn_lib_more = itemView.findViewById (R.id.btn_rec1_edu_vid);


    }
  }
}
