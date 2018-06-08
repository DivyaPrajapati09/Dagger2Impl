package com.example.acquaint.daggerdemo.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.acquaint.daggerdemo.component.MyInterfaceImpl;
import com.example.acquaint.daggerdemo.interfaces.ApplicationContext;
import com.example.acquaint.daggerdemo.interfaces.DatabaseInfo;
import com.example.acquaint.daggerdemo.interfaces.MyInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application app) {
    mApplication = app;
    }

    @Provides
    @Singleton
    static MyInterface getModuleImpl(){
        return new MyInterfaceImpl();
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }


    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return "demo-dagger.db";
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion() {
        return 2;
    }
}
