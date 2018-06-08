package com.example.acquaint.daggerdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.acquaint.daggerdemo.R;
import com.example.acquaint.daggerdemo.app.ApplicationClass;
import com.example.acquaint.daggerdemo.app.DataManager;
import com.example.acquaint.daggerdemo.component.ActivityComponent;
import com.example.acquaint.daggerdemo.component.DaggerActivityComponent;
import com.example.acquaint.daggerdemo.model.User;
import com.example.acquaint.daggerdemo.modules.ActivityModule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    DataManager mDataManager;
    private String TAG = MainActivity.class.getSimpleName();
    private ActivityComponent activityComponent;
    private EditText etName;
    private EditText etSurname;
    private EditText etPhone;
    private List<User> listOfUser = new ArrayList<>();

    private View.OnClickListener mOnSaveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name= etName.getText().toString().trim();
            String surname = etSurname.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            User user = new User(name,surname,phone);
            try {
                mDataManager.createUser(user);
                etName.setText("");
                etSurname.setText("");
                etPhone.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private View.OnClickListener mOnListClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this,RetrofitCallActivity.class));
        }
    };

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
        Log.e(TAG, "onCreate: "+mDataManager.getData());
        etName = findViewById(R.id.et_name);
        etSurname = findViewById(R.id.et_surname);
        etPhone = findViewById(R.id.et_phone);
       TextView txtDate = findViewById(R.id.txt_current_time);
        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(mOnSaveClickListener);
        Button btnList = findViewById(R.id.btn_list);
        btnList.setOnClickListener(mOnListClicklistener);

        Log.e(TAG, "onCreate: before : "+mDataManager.getData());
        mDataManager.saveData(new Date().toString());
        Log.e(TAG, "onCreate: after : "+mDataManager.getData());
        txtDate.setText(mDataManager.getData());
    }
}
