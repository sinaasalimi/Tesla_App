package com.example.test_bottom.itemOfFragments;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.example.test_bottom.R;

import androidx.fragment.app.Fragment;


public class Wolfram_Alpha extends Fragment {


  EditText edt_function;
  WebView webView;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate (R.layout.fragment_wolfram_alpha, container, false);


    edt_function = view.findViewById (R.id.edt_function);
    webView = view.findViewById (R.id.webview);

    edt_function.setOnKeyListener (new View.OnKeyListener () {
      @Override
      public boolean onKey(View v, int keyCode, KeyEvent event) {


        String s_input =  edt_function.getText ().toString ();
        String[] s_input_split = s_input.split ("");
        String static_link = "https://www.wolframalpha.com/input/?i=";
        String s_output = "";

        if(keyCode == KeyEvent.KEYCODE_ENTER){




          for (int i = 0;i<s_input_split.length;i++){

            switch (s_input_split[i]){

              case "(":
                s_input_split[i] = "%28";

                break;

              case ")":
                s_input_split[i] = "%29";
                break;

              case "+":
                s_input_split[i] = "%2B";
                break;

              case "=":
                s_input_split[i] = "%3D";
                break;

              case "/":
                s_input_split[i] = "%2F";
                break;

              case " " :
                s_input_split[i] = "+"  ;
                break;

              case "^":
                s_input_split[i] = "%5E";
                break;

            }

          }



          for (int i = 0 ; i< s_input_split.length; i++){

            s_output = s_output + s_input_split[i];
          }


          webView.getSettings ().setJavaScriptEnabled (true);
          webView.loadUrl (static_link + s_output);
          webView.setWebViewClient (new myWebViewClient ());
          return true;
        } else {
          return false;
        }
      }
    });


    return view;
  }



  public class myWebViewClient extends WebViewClient{
    ProgressDialog progressDialog = new ProgressDialog (getContext ());
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
      view.loadUrl (url);
      return true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {


      progressDialog.setMessage ("Loading Page");
      progressDialog.show ();

    }

    @Override
    public void onPageFinished(WebView view, String url) {
      progressDialog.cancel ();
    }
  }
}