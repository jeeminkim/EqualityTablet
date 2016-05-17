package com.example.daniel.equalitytablet.Fragment;

import android.app.Service;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.daniel.equalitytablet.MainActivity;
import com.example.daniel.equalitytablet.R;


public class FunctionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_function, container, false);
        Log.d("FunctionFragment", "onCreateView()");



        ((Button)v.findViewById(R.id.btn001)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = mHandler.obtainMessage();
                msg.what = MainActivity.BT_RESULT_WRITE;
                msg.obj = (String) ("혈압 측정 종료 / 연산 " + "S" + ":0" + "\n");
                mHandler.sendMessage(msg);
                Toast.makeText(getActivity().getApplicationContext(), "혈압 측정을 종료하고 혈압 평균 값을 구합니다.", Toast.LENGTH_SHORT).show();
            }
        });

        ((Button)v.findViewById(R.id.btn002)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.what = MainActivity.BT_RESULT_WRITE;
                msg.obj = (String)("연산 정보 저장 " + "S"+":1" +"\n");
                mHandler.sendMessage(msg);
                Toast.makeText(getActivity().getApplicationContext(), "입력된 사람의 혈압 평균 값을 저장합니다.", Toast.LENGTH_SHORT).show();
            }
        });

        ((Button)v.findViewById(R.id.btn003)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Message msg = new Message();
                msg.what = MainActivity.BT_RESULT_WRITE;
                msg.obj = (String)("체온계 측정 종료 / 연산 " + "S"+":2" +"\n");
                mHandler.sendMessage(msg);
                Toast.makeText(getActivity().getApplicationContext(), "체온계 측정을 종료하고 평균을 구합니다.", Toast.LENGTH_SHORT).show();
            }
        });

        ((Button)v.findViewById(R.id.btn004)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Message msg = new Message();
                msg.what = MainActivity.BT_RESULT_WRITE;
                msg.obj = (String)("연산 정보 저장 " + "S"+":3" +"\n");
                mHandler.sendMessage(msg);
                Toast.makeText(getActivity().getApplicationContext(), "입력된 사람의 체온 평균 값을 저장합니다.", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }



    Handler mHandler = null;
    public void setHandler(Handler mHandler){
        this.mHandler = mHandler;
    }
}

