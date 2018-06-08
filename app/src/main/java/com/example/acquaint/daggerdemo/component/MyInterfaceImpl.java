package com.example.acquaint.daggerdemo.component;

import com.example.acquaint.daggerdemo.interfaces.MyInterface;

import java.util.Date;

public class MyInterfaceImpl implements MyInterface {

    private String date;

    public MyInterfaceImpl() {
        date =new Date().toString();
    }

    public String getDate(){
        return date;
    }
}
