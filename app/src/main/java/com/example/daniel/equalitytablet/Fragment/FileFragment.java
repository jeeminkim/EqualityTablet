package com.example.daniel.equalitytablet.Fragment;

/**
 * Created by Daniel on 2016-05-13.
 */



import android.content.Intent;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.daniel.equalitytablet.FileRead;
import com.example.daniel.equalitytablet.FileSave;
import com.example.daniel.equalitytablet.R;


public class FileFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_file,container,false);

        ((Button)v.findViewById(R.id.btn1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity().getApplicationContext(),FileSave.class);
                startActivity(intent);
            }
        });

        ((Button)v.findViewById(R.id.btn2)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity().getApplicationContext(),FileRead.class);
                startActivity(intent);
            }
        });
        return v;
    }

}
