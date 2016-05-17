package com.example.daniel.equalitytablet;

/**
 * Created by Daniel on 2016-05-13.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileSave extends Activity{
    private FileOutputStream fos;
    private EditText et;
    Date date;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filesave);
        et=(EditText)findViewById(R.id.editText);
    }
    public void save(View view){
       // CommData data = getItem(position);
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowdate = new Date();
        String dateString = sdf.format(nowdate);
        try{
            fos=openFileOutput("symptoms2.txt",MODE_APPEND);
            fos.write(et.getText().toString().getBytes());
            fos.write(dateString.getBytes());
            //fos.write(sdf.getText().toString().getBytes());
            fos.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
