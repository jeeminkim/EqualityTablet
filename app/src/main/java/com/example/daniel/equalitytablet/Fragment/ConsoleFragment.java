package com.example.daniel.equalitytablet.Fragment;


import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import com.example.daniel.equalitytablet.define.CommData;
import com.example.daniel.equalitytablet.MainActivity;
import com.example.daniel.equalitytablet.R;


public class ConsoleFragment extends Fragment {
    MainActivity activity;
    ListView lvData;
    Button btnSend;
    EditText edtText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_console, container, false);
        Log.d("ConsoleFragment", "onCreateView()");
        this.activity  = (MainActivity)getActivity();

        init(v);
        return v;
    }
    void init(View v){
        lvData = (ListView)v.findViewById(R.id.lvData);
        commArrayAdapter = new CommArrayAdapter(activity, 0, activity.alCommData);
        lvData.setAdapter(commArrayAdapter);


        edtText = (EditText)v.findViewById(R.id.edtText);
        edtText.addTextChangedListener(textWatcher);

        btnSend = (Button)v.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtText.getText().length() > 0) {
                    Message msg = new Message();
                    msg.what = MainActivity.BT_RESULT_WRITE;
                    msg.obj = (String)(edtText.getText().toString() + "\n");
                    mHandler.sendMessage(msg);
                }
            }
        });
        im = (InputMethodManager) activity.getSystemService
                (Service.INPUT_METHOD_SERVICE);

    }
    InputMethodManager im;
    public void hideKeyboard(){
        im.hideSoftInputFromWindow(edtText.getWindowToken(), 0);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.d("ConsoleFragemnt", "beforeTextChanged " + s);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.d("ConsoleFragemnt", "onTextChanged " + s);
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.d("ConsoleFragemnt", "afterTextChanged " + s);
        }
    };

    Handler mHandler = null;

    public void setHandler(Handler m){
        this.mHandler = m;
    }

    public void refresh(){
        commArrayAdapter.notifyDataSetChanged();
        lvData.smoothScrollToPosition(commArrayAdapter.getCount() -1);
    }

    public CommArrayAdapter commArrayAdapter;

    private class CommArrayAdapter extends ArrayAdapter<CommData> {
        LayoutInflater inflater;
        Context context;
        SimpleDateFormat sdf;
        private class Viewholder {
            TextView tvUser;
            TextView tvData;
            TextView tvDate;
        }
        public CommArrayAdapter(Context context, int resource, List<CommData> objects) {
            super(context, resource, objects);
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            CommData data = getItem(position);
            return data.getUser();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Viewholder holder;
            if(convertView == null){
                if(getItemViewType(position) == CommData.SEND){
                    convertView = inflater.inflate(R.layout.item_snd, null);
                } else {
                    convertView = inflater.inflate(R.layout.item_rcv, null);
                }
                holder = new Viewholder();
                holder.tvUser =(TextView)convertView.findViewById(R.id.tvUser);
                holder.tvDate =(TextView)convertView.findViewById(R.id.tvDate);
                holder.tvData =(TextView)convertView.findViewById(R.id.tvData);
                convertView.setTag(holder);

            } else {
                holder = (Viewholder)convertView.getTag();
            }

            CommData data = getItem(position);
            if(data.getUser() == CommData.SEND){
                holder.tvUser.setText("SEND");
            } else if(data.getUser() == CommData.RECEIVE){
                holder.tvUser.setText("RECEIVE");
            }
            holder.tvDate.setText(sdf.format(data.getDate()));
            holder.tvData.setText(""+ data.getData());


            return convertView;
        }
    }

}
