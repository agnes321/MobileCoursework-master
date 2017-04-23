package com.example.a1408876.films;

/**
 * Created by 1408876 on 12/04/2017.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class About extends Fragment{
    //private Button bttnW;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about, container, false);
//        bttnW = (Button) rootView.findViewById(R.id.openWatchlist);
//
//        bttnW.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(About.this.getActivity(), Watchlist.class);
//                startActivity(intent);
//            }
//        });


        return rootView;
    }


}
