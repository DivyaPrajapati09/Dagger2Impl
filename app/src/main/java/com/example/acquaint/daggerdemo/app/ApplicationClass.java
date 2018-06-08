package com.example.acquaint.daggerdemo.app;

import android.app.Application;
import android.content.Context;

import com.example.acquaint.daggerdemo.component.ApplicationComponent;
import com.example.acquaint.daggerdemo.component.DaggerApplicationComponent;
import com.example.acquaint.daggerdemo.modules.ApplicationModule;

import javax.inject.Inject;

public class ApplicationClass extends Application{

    @Inject
    DataManager dataManager;
    private ApplicationComponent applicationComponent;

    public static ApplicationClass get(Context context) {
        return (ApplicationClass) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getComponent(){
        return applicationComponent;
    }
}
