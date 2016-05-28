package com.example.daniel.equalitytablet.Fragment;

/**
 * Created by Daniel on 2016-05-13.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.daniel.equalitytablet.AnimalState;
import com.example.daniel.equalitytablet.FileRead;
import com.example.daniel.equalitytablet.FileSave;
import com.example.daniel.equalitytablet.PersonState;
import com.example.daniel.equalitytablet.R;
import com.example.daniel.equalitytablet.Scope;

public class SelectFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_select,container,false);

        ((Button)v.findViewById(R.id.btn3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity().getApplicationContext(),PersonState.class);
                startActivity(intent);
            }
        });

        ((Button)v.findViewById(R.id.btn4)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity().getApplicationContext(),AnimalState.class);
                startActivity(intent);
            }
        });

        ((Button)v.findViewById(R.id.btn5)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity().getApplicationContext(),Scope.class);
                startActivity(intent);
            }
        });
        return v;
    }

}
