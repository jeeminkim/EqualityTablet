package com.example.daniel.equalitytablet;

/**
 * Created by Daniel on 2016-05-13.
 */
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;



public class AnimalState extends Activity {



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animalstate);


    }
    public void go(View view){

        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://terms.naver.com/entry.nhn?docId=742195&cid=46681&categoryId=46681")));
        //String urlStr=et.getText().toString();
    }


}
