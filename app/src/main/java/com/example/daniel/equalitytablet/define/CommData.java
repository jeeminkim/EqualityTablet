package com.example.daniel.equalitytablet.define;

import java.util.Date;

public class CommData {
    public static final int SEND = 0;
    public static final int RECEIVE = 1;

    int user = RECEIVE;
    String data;
    Date date;
    public CommData(){
        this.user = RECEIVE;
        this.data = "";
        this.date = new Date() ;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
