package com.example.acquaint.daggerdemo.app;

import android.app.Application;

import com.example.acquaint.daggerdemo.component.DaggerMyComponent;
import com.example.acquaint.daggerdemo.component.MyComponent;
import com.example.acquaint.daggerdemo.modules.MyModule;

public class ApplicationClass extends Application{

    private MyComponent mMyComponent;

    public MyComponent getmMyComponent() {
        return mMyComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMyComponent= DaggerMyComponent.builder()
                .myModule(new MyModule()).build();
    }
}
