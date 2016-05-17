package com.example.daniel.equalitytablet.SMS;

/**
 * Created by Daniel on 2016-05-13.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    public SMSReceiver(){

    }
    @Override
    public void onReceive(Context context,Intent intent){
        Bundle bundle = intent.getExtras();
        String msg;
        String sender;
        int i;

        if(bundle !=null){
            Object rawData[]=(Object[])bundle.get("pdus");
            SmsMessage[] sms = new SmsMessage[rawData.length];
            for(i=0;i<rawData.length;i++){
            sms[i]=SmsMessage.createFromPdu((byte[])rawData[i]);
            }
            for(i=0;i<sms.length;i++){
                msg=sms[i].getMessageBody();
                sender = sms[i].getOriginatingAddress();
                Toast.makeText(context,msg+"from"+sender,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
