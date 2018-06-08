package com.example.acquaint.daggerdemo.component;

import com.example.acquaint.daggerdemo.activity.ListActivity;
import com.example.acquaint.daggerdemo.activity.MainActivity;
import com.example.acquaint.daggerdemo.interfaces.PerActivity;
import com.example.acquaint.daggerdemo.modules.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(ListActivity listActivity);
}
