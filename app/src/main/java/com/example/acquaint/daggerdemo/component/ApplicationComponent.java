package com.example.acquaint.daggerdemo.component;

import android.app.Application;
import android.content.Context;

import com.example.acquaint.daggerdemo.app.ApplicationClass;
import com.example.acquaint.daggerdemo.app.DataManager;
import com.example.acquaint.daggerdemo.app.SharedPrefsHelper;
import com.example.acquaint.daggerdemo.db.DbHelper;
import com.example.acquaint.daggerdemo.interfaces.ApplicationContext;
import com.example.acquaint.daggerdemo.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(ApplicationClass applicationClass);


    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();

    SharedPrefsHelper getSharedPrefs();

    DbHelper getDbHelper();
}
