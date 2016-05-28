package com.example.daniel.equalitytablet;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.daniel.equalitytablet.Bluetooth.BluetoothService;
import com.example.daniel.equalitytablet.Fragment.FileFragment;
import com.example.daniel.equalitytablet.Fragment.RecordFragment;
import com.example.daniel.equalitytablet.Fragment.SelectFragment;
import com.example.daniel.equalitytablet.define.CommData;
import com.example.daniel.equalitytablet.Fragment.ConsoleFragment;
import com.example.daniel.equalitytablet.Fragment.FunctionFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = getClass().getName();
    BackPressCloseHandler backPressCloseHandler;
    ConsoleFragment consoleFragment = null;
    FunctionFragment functionFragment = null;
    FileFragment fileFragment = null;
    SelectFragment selectFragment =null;
    RecordFragment recordFragment = null;
    public static final int BT_RESULT_CONNECTED = 0x30;
    public static final int BT_RESULT_DISCONNECTED = 0x31;
    public static final int BT_RESULT_WRITE = 0x32;
    public static final int BT_RESULT_READ = 0x33;
    boolean isConnected = false;

    public ArrayList<CommData> alCommData = new ArrayList<CommData>();
    StringBuffer sb = new StringBuffer();
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case BT_RESULT_CONNECTED:
                    Log.d(TAG, "BT_RESULT_CONNECTED");
                    isConnected = true;
                    break;
                case BT_RESULT_DISCONNECTED:
                    Log.d(TAG, "BT_RESULT_DISCONNECTED");
                    isConnected = false;
                    break;
                case BT_RESULT_READ:
                    int bytes = msg.arg1;
                    byte[] buffer = new byte[bytes];
                    buffer = (byte[])msg.obj;
                    String str = new String(buffer).substring(0, bytes);
                    if(str.endsWith("\n")){
                        sb.append(str);
                        Log.d(TAG, "READ STRING :" + sb.toString());
                        CommData comm = new CommData();
                        comm.setData(sb.toString());
                        alCommData.add(comm);

                        consoleFragment.refresh();

                        sb = new StringBuffer();
                    } else {
                        sb.append(str);
                    }
                    break;
                case BT_RESULT_WRITE:
                    String data = (String)msg.obj;
                    Log.d(TAG, "BT_RESULT_WRITE : " + data);
                    btService.write(data.getBytes());
                    CommData comm = new CommData();
                    comm.setUser(CommData.SEND);
                    comm.setData(data);
                    alCommData.add(comm);

                    consoleFragment.refresh();
                    break;
            }
        }
    };

    private BluetoothService btService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "의료 지원팀에게 전화합니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01089432301"));
                startActivity(intent);
            }
        });

        if(savedInstanceState == null) {
            if(consoleFragment == null){
                consoleFragment = new ConsoleFragment();
                consoleFragment.setHandler(mHandler);
            }
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, consoleFragment)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        DrawerToggle toggle = new DrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(btService == null) {
            btService = new BluetoothService(this, mHandler);
        }

    }

    private class DrawerToggle extends  ActionBarDrawerToggle{

        public DrawerToggle(Activity activity, DrawerLayout drawerLayout, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
            super(activity, drawerLayout, openDrawerContentDescRes, closeDrawerContentDescRes);
        }

        public DrawerToggle(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int openDrawerContentDescRes, int closeDrawerContentDescRes) {
            super(activity, drawerLayout, toolbar, openDrawerContentDescRes, closeDrawerContentDescRes);
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
            Log.d("MainActivity", "onDrawerOpened");
            consoleFragment.hideKeyboard();
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
            Log.d("MainActivity", "onDrawerClosed");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            backPressCloseHandler.onBackPressed();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case BluetoothService.REQUEST_ENABLE_BT:
                if(resultCode == Activity.RESULT_OK) {
                    btService.scanDevice();
                } else {
                }
                break;
            case BluetoothService.REQUEST_CONNECT_DEVICE:
                if(resultCode == Activity.RESULT_OK) {
                    btService.getDeviceInfo(data);
                }
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "설정", Toast.LENGTH_LONG).show();
            return true;
        } else if(id == R.id.action_connect){
            // Bluetooth Connect
            if(!isConnected) {
                if(btService.getDeviceStatus()){
                    btService.enableBluetooth();
                } else {
                }
            } else {
                Toast.makeText(this, "Bluetooth is Ready!", Toast.LENGTH_LONG).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_console) {
            // Handle the camera action
            Toast.makeText(this, "데이터 송수신 확인", Toast.LENGTH_LONG).show();
            if(consoleFragment == null){
                consoleFragment = new ConsoleFragment();
                consoleFragment.setHandler(mHandler);
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, consoleFragment)
                    .commit();
        }
        else if (id == R.id.nav_file) {
            Toast.makeText(this, "증상 기록/불러오기", Toast.LENGTH_LONG).show();
            if(fileFragment == null){
                fileFragment = new FileFragment();
               // fileFragment.setHandler(mHandler);
            }
            consoleFragment.hideKeyboard();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fileFragment)
                    .commit();
        }
        else if (id == R.id.nav_human) {
            Toast.makeText(this, "증상 알아보기", Toast.LENGTH_LONG).show();
            if(selectFragment == null){
                selectFragment = new SelectFragment();
                // fileFragment.setHandler(mHandler);
            }
            consoleFragment.hideKeyboard();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, selectFragment)
                    .commit();
        }
        else if (id == R.id.nav_record) {
            Toast.makeText(this, "건강 정보 기록소", Toast.LENGTH_LONG).show();
            if(recordFragment == null){
                recordFragment = new RecordFragment();
                // fileFragment.setHandler(mHandler);
            }
            consoleFragment.hideKeyboard();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, recordFragment)
                    .commit();
        }
         else if (id == R.id.nav_function) {
            Toast.makeText(this, "기능 버튼", Toast.LENGTH_LONG).show();
            if(functionFragment == null){
                functionFragment = new FunctionFragment();
                functionFragment.setHandler(mHandler);
            }
            consoleFragment.hideKeyboard();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, functionFragment)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
