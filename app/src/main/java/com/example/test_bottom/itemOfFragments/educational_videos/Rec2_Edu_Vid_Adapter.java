package com.example.test_bottom.itemOfFragments.educational_videos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test_bottom.R;

import org.json.JSONArray;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class Rec2_Edu_Vid_Adapter extends RecyclerView.Adapter<Rec2_Edu_Vid_Adapter.ViewHolder> {

  Context context;
  JSONArray jsonArray;

  public Rec2_Edu_Vid_Adapter(Context context, JSONArray jsonArray) {
    this.context = context;
    this.jsonArray = jsonArray;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view  = LayoutInflater.from (parent.getContext ()).inflate (R.layout.rec2_edu_vid,parent,false);
    return new ViewHolder (view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return jsonArray.length ();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(@NonNull View itemView) {
      super (itemView);
    }
  }
}
