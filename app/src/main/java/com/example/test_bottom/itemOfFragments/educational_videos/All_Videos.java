package com.example.test_bottom.itemOfFragments.educational_videos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test_bottom.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class All_Videos extends Fragment {


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate (R.layout.all_videos,container,false);




    return super.onCreateView (inflater, container, savedInstanceState);
  }
}

