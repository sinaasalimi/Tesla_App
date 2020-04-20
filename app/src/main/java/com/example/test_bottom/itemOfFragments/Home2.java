package com.example.test_bottom.itemOfFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test_bottom.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import static androidx.navigation.Navigation.findNavController;


public class Home2 extends Fragment  {

   AppBarConfiguration appBarConfiguration;



  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View root = inflater.inflate (R.layout.home2,container,false);

    String jsonString = getArguments ().getString ("JsonSecObject");

    BottomNavigationView navView = root.findViewById (R.id.bottom_nav_view);

    appBarConfiguration = new AppBarConfiguration.Builder (
      R.id.library, R.id.edu_vid)
      .build ();

    View view  = root.findViewById (R.id.nav_host_fragment2);
    NavController navController = findNavController (view);
    //NavigationUI.setupActionBarWithNavController ((AppCompatActivity) getActivity (), navController, appBarConfiguration);
    NavigationUI.setupWithNavController (navView, navController);


    return root;
  }





}
