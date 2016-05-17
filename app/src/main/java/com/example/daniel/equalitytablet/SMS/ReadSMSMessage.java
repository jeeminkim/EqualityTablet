package com.example.daniel.equalitytablet.SMS;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.daniel.equalitytablet.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 2016-05-15.
 */

public class ReadSMSMessage extends Activity {

    public static ArrayList<Map<String, Object>> ReadSMSMessage(Context context) {
        ArrayList<Map<String, Object>> mList;
        try {
            mList = new ArrayList<Map<String, Object>>();
            String[] str = {"_id", "thread_id", "address", "person", "date", "body", "subject", "service_center", "locked", "error_code", "seen"};

            Uri allMessage = Uri.parse("content://sms");
            ContentResolver cr = context.getContentResolver();
            Cursor cursor = cr.query(allMessage, str, null, null, "date DESC");

            while (cursor.moveToNext()) {
                long messageId = cursor.getLong(0);
                long threadId = cursor.getLong(1);
                String address = cursor.getString(2);
                long contactId = cursor.getLong(3);
                String contactId_string = String.valueOf(contactId);
                long timestamp = cursor.getLong(4);
                String body = cursor.getString(5);

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("_id", messageId);
                map.put("thread_id", threadId);
                map.put("address", address);
                map.put("person", contactId);
                map.put("person_string", contactId_string);
                map.put("date", timestamp);
                map.put("body", body);
                map.put("isChecked", false);

                mList.add(map);
            }
            return mList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Map<String, Object>>();
        }
    }
}

/*
    public int ReadMessage(){
        Uri allMessage =  Uri.parse("content://sms/inbox");
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(allMessage, new String[]{"_id", "thread_id", "address",
                "person", "date", "body"}, null, null, "date DESC");
        String string = "";
        int count =0;
        while(c.moveToNext()){
            long messageId = c.getLong(0);
            long threadId=c.getLong(1);
            String address = c.getString(2);
            long contactId = c.getLong(3);
            String contactId_string =String.valueOf(contactId);
            long timestamp = c.getLong(4);
            String body = c.getString(5);
            string = String.format("msgid:%d,thread:%d,address:%S,"+"contactid:%d,contactstring:%s, timestamp:%d,body:%s?",
                    messageId,threadId,address,contactId,contactId_string,timestamp,body);
            Log.d("maluchi",++count+"st,message:"+string);
        }
        return 0;
    }*/

