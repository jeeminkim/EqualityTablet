package com.example.daniel.equalitytablet;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;



public class FileRead extends Activity {
    private FileInputStream fis;
    private TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fileread);
        tv=(TextView)findViewById(R.id.textView);
    }
    public void read(View view) {
    try{
        fis=openFileInput("symptoms4.txt");
        StringBuffer sbuf=new StringBuffer();
        byte[]buf=new byte[40];
        while((fis.read(buf,0,40))!=-1){
            String str= new String(buf);
            sbuf.append(str);
            if(fis.available()<40){
                Arrays.fill(buf,0,40,(byte)'0');
            }
            fis.close();
            tv.setText(sbuf);
          }
       }catch(FileNotFoundException e){
        e.printStackTrace();
    }catch(IOException e){
        e.printStackTrace();
    }
    }
}
