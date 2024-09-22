package com.yael.springboot.api.gchat.gchat.application.services;

import java.util.Date;



public class ResponseService<T> {

    private Date date = new Date();
    private T data;
    private int status;
    private String token;


    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
