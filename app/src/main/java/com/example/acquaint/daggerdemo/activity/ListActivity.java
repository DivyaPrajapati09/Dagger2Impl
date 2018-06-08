package com.example.acquaint.daggerdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.acquaint.daggerdemo.R;
import com.example.acquaint.daggerdemo.adapters.PersonListAdapter;
import com.example.acquaint.daggerdemo.app.ApplicationClass;
import com.example.acquaint.daggerdemo.app.DataManager;
import com.example.acquaint.daggerdemo.component.ActivityComponent;
import com.example.acquaint.daggerdemo.component.DaggerActivityComponent;
import com.example.acquaint.daggerdemo.modules.ActivityModule;

import javax.inject.Inject;

public class ListActivity extends AppCompatActivity{

    @Inject
    DataManager dataManager;
    private ActivityComponent activityComponent;

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(ApplicationClass.get(this).getComponent())
                    .build();
        }
        return activityComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getActivityComponent().inject(this);
        RecyclerView rvPersons = findViewById(R.id.rv_list);
        rvPersons.setLayoutManager(new LinearLayoutManager(this));
        PersonListAdapter personListAdapter = new PersonListAdapter(this,dataManager.getUser());
        rvPersons.setAdapter(personListAdapter);
    }
}
