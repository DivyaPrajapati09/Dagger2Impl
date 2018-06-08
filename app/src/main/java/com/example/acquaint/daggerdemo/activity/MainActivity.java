package com.example.acquaint.daggerdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.acquaint.daggerdemo.R;
import com.example.acquaint.daggerdemo.app.ApplicationClass;
import com.example.acquaint.daggerdemo.interfaces.MyInterface;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {


    @Inject
    MyInterface mMyInterface;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((ApplicationClass)getApplicationContext()).getmMyComponent().inject(this);
        Log.e(TAG, "onCreate: "+mMyInterface.getDate() );
        TextView txtDate = findViewById(R.id.txt_current_time);
        txtDate.setText(mMyInterface.getDate());
    }
}
