package com.example.daniel.equalitytablet;

/**
 * Created by Daniel on 2016-05-13.
 */
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;


public class PersonState extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personstate);

    }
    public void go1(View view){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://terms.naver.com/entry.nhn?docId=926798&cid=51007&categoryId=51007")));
    }


}
