package com.example.acquaint.daggerdemo.modules;

import com.example.acquaint.daggerdemo.component.MyInterfaceImpl;
import com.example.acquaint.daggerdemo.interfaces.MyInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MyModule {

    @Provides
    @Singleton
    static MyInterface getModuleImpl(){
        return new MyInterfaceImpl();
    }
}
