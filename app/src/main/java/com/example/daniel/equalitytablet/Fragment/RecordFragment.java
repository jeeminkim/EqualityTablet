package com.example.daniel.equalitytablet.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.daniel.equalitytablet.AnimalState;
import com.example.daniel.equalitytablet.FileRead;
import com.example.daniel.equalitytablet.PersonState;
import com.example.daniel.equalitytablet.R;
import com.example.daniel.equalitytablet.SMS.ReadSMSMessage;
import com.example.daniel.equalitytablet.SMS.SendSMS;
import com.example.daniel.equalitytablet.Weather;

/**
 * Created by Daniel on 2016-05-15.
 */

public class RecordFragment extends Fragment {

    final String PhoneNum ="01089432301";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_record,container,false);

        ((Button)v.findViewById(R.id.btn10)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity().getApplicationContext(),ReadSMSMessage.class);
                startActivity(intent);
            }
        });
        ((Button)v.findViewById(R.id.btn11)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity().getApplicationContext(),SendSMS.class);
                startActivity(intent);
            }
        });
        ((Button)v.findViewById(R.id.btn12)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity().getApplicationContext(),Weather.class);
                startActivity(intent);
            }
        });

        return v;
    }


}

