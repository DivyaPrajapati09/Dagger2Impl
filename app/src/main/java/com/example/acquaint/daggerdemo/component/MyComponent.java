package com.example.acquaint.daggerdemo.component;

import com.example.acquaint.daggerdemo.activity.MainActivity;
import com.example.acquaint.daggerdemo.modules.MyModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MyModule.class)
public interface MyComponent {

    void inject(MainActivity mainActivity);

}
