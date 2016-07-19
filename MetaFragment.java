package com.monicapp1.monicapp1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MetaFragment extends Fragment{

    Activity activity;
    FloatingActionButton fab;

    public MetaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_meta, container, false);
        fab=(FloatingActionButton)view.findViewById(R.id.otrameta);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity=getActivity();
                Toast.makeText(activity, "Por fin" , Toast.LENGTH_SHORT).show();
            }
        });
        return view;
        */
        return inflater.inflate(R.layout.fragment_meta, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // Set up 1 action button
        inflater.inflate(R.menu.menu_meta, menu);
    }

}
