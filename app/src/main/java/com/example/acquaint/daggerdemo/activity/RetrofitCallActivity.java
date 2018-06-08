package com.example.acquaint.daggerdemo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.acquaint.daggerdemo.R;
import com.example.acquaint.daggerdemo.component.DaggerRetrofitComponent;
import com.example.acquaint.daggerdemo.component.RetrofitComponent;
import com.example.acquaint.daggerdemo.interfaces.network_interfaces.Api;
import com.example.acquaint.daggerdemo.model.Hero;
import com.example.acquaint.daggerdemo.modules.ApplicationModule;
import com.example.acquaint.daggerdemo.modules.RetrofitModule;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitCallActivity extends AppCompatActivity{

    private static final String TAG = RetrofitCallActivity.class.getSimpleName();
    @Inject
    Retrofit retrofit;
    private RetrofitComponent retrofitComponent;

    public RetrofitComponent getRetrofitComponent() {
        if (retrofitComponent == null) {
            retrofitComponent = DaggerRetrofitComponent.builder()
                    .applicationModule(new ApplicationModule(this.getApplication()))
                    .retrofitModule(new RetrofitModule("https://simplifiedcoding.net/demos/"))
                    .build();
        }
        return retrofitComponent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_list);
        getRetrofitComponent().inject(this);
        getList();
    }

    private void getList() {
        Api api = retrofit.create(Api.class);
        api.getHeroes().enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(@NonNull Call<List<Hero>> call, @NonNull Response<List<Hero>> response) {
                Log.e(TAG, "onResponse: "+response.body());
                if(response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        Log.e(TAG, "onResponse: " + response.body().size());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Hero>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}