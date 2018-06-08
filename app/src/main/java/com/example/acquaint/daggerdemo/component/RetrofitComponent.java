package com.example.acquaint.daggerdemo.component;

import com.example.acquaint.daggerdemo.activity.RetrofitCallActivity;
import com.example.acquaint.daggerdemo.modules.ApplicationModule;
import com.example.acquaint.daggerdemo.modules.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class,RetrofitModule.class})
public interface RetrofitComponent {

    void inject(RetrofitCallActivity retrofitCallActivity);
}
