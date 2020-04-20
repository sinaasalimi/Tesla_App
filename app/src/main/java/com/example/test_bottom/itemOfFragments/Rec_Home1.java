package com.example.test_bottom.itemOfFragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_bottom.GetJson;
import com.example.test_bottom.R;
import com.example.test_bottom.Cons;
import com.example.test_bottom.itemOfFragments.library.Library;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.navigation.Navigation.findNavController;

class Rec_Home1 extends RecyclerView.Adapter<Rec_Home1.ViewHolder> {

  Context context;
  JSONArray jsonArray;

  public Rec_Home1(Context context, JSONArray jsonArray) {
    this.context = context;
    this.jsonArray = jsonArray;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view  = LayoutInflater.from (parent.getContext ()).inflate (R.layout.rec_home1,parent,false);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    try {
      final JSONObject jsonObject = jsonArray.getJSONObject (position);
      Picasso.get ()
        .load (jsonObject.getString ("Image"))
        .resize (holder.img_rec_home1.getWidth (),holder.img_rec_home1.getHeight ())
        .into (holder.img_rec_home1);
      holder.txt_group_name_rec_home1.setText (jsonObject.getString ("GroupName"));

      holder.cardview.setOnClickListener (new View.OnClickListener () {
        @Override
        public void onClick(View v) {

          try {

            new GetJsonSec (
              jsonObject.getString ("JsonSecObject"),jsonObject.getString ("GroupName)"))
                  .execute ();

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
  public int getItemCount() {
    return 0;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {


    ImageView img_rec_home1;
    TextView  txt_group_name_rec_home1;
    CardView cardview ;

    public ViewHolder(@NonNull View itemView) {
      super (itemView);


      img_rec_home1 = itemView.findViewById (R.id.img_rec_home1);
      txt_group_name_rec_home1 = img_rec_home1.findViewById (R.id.txt_group_name_rec_home1);
      cardview = itemView.findViewById (R.id.cardView);


    }
  }

  public class GetJsonSec extends GetJson {


    String jsonFileName ;



    public GetJsonSec(String jsonUrl,String jsonFileName ) {
      super (context, jsonUrl);

      this.jsonFileName = jsonFileName;

    }

    @Override
    protected void onPostExecute(String s) {
      super.onPostExecute (s);

      if(!s.equals ("error")){

        File file = new File (new Cons ().cash_directory + jsonFileName + ".txt");
        if (file.exists ()){

          findNavController((Activity) context,R.id.nav_host_fragment1)
            .navigate (R.id.action_home1_to_home2);

        }else {
          try {
            file.createNewFile ();
            BufferedWriter writer = new BufferedWriter (new FileWriter (file));
            writer.write (s);
            writer.close ();

            Fragment library = new Library ();
            Bundle bundle = new Bundle ();
            bundle.putString ("JsonSecPath",file.getAbsolutePath ());
            library.setArguments (bundle);


            findNavController((Activity) context,R.id.nav_host_fragment1)
              .navigate (R.id.action_home1_to_home2);

          } catch (IOException e) {
            e.printStackTrace ();
          }


        }

      }else {

        Toast.makeText (context,"error in Rec_Home1",Toast.LENGTH_LONG).show ();

      }


    }
  }


}
