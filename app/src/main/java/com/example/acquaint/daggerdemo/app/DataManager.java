package com.example.acquaint.daggerdemo.app;

import android.content.Context;

import com.example.acquaint.daggerdemo.db.DbHelper;
import com.example.acquaint.daggerdemo.interfaces.ApplicationContext;
import com.example.acquaint.daggerdemo.model.User;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManager {
    private Context mContext;
    private SharedPrefsHelper mSharedPrefsHelper;
    private DbHelper mDbHelper;

    @Inject
    public DataManager(@ApplicationContext Context mContext, SharedPrefsHelper mSharedPrefsHelper, DbHelper dbHelper) {
        this.mContext = mContext;
        this.mSharedPrefsHelper = mSharedPrefsHelper;
        this.mDbHelper = dbHelper;
    }

    public void saveData(String data){
        mSharedPrefsHelper.put("ACCESS_TOKEN",data);
    }

    public String getData(){
        return mSharedPrefsHelper.get("ACCESS_TOKEN",new Date().toString());
    }

    public Long createUser(User user) throws Exception {
        return mDbHelper.insertUser(user);
    }

    public List<User> getUser() {
        return mDbHelper.getUser();
    }
}
