package com.example.daniel.equalitytablet;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Daniel on 2016-05-28.
 */
public class Scope extends Activity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scope);

    }

    public void a(View view){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://terms.naver.com/entry.nhn?docId=926590&mobile&cid=51007&categoryId=51007")));
    }
    public void b(View view){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://terms.naver.com/entry.nhn?docId=926583&mobile&cid=51007&categoryId=51007")));
    }
    public void c(View view){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://terms.naver.com/entry.nhn?docId=926609&mobile&cid=51007&categoryId=51007")));
    }
    public void e(View view){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://terms.naver.com/entry.nhn?docId=926614&mobile&cid=51007&categoryId=51007")));
    }
    public void d(View view){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://terms.naver.com/entry.nhn?docId=926609&mobile&cid=51007&categoryId=51007")));
    }
    public void f(View view){
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://terms.naver.com/entry.nhn?docId=926590&mobile&cid=51007&categoryId=51007")));
    }



}
