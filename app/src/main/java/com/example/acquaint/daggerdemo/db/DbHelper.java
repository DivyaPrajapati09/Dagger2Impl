package com.example.acquaint.daggerdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.acquaint.daggerdemo.interfaces.ApplicationContext;
import com.example.acquaint.daggerdemo.interfaces.DatabaseInfo;
import com.example.acquaint.daggerdemo.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DbHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE_NAME = "users";

    public static final String USER_COLUMN_USER_ID = "id";
    public static final String USER_COLUMN_USER_NAME = "usr_name";
    public static final String USER_COLUMN_USER_SURNAME = "usr_surname";
    public static final String USER_COLUMN_USER_CONTACT_NUM = "usr_contact_num";

    @Inject
    public DbHelper(@ApplicationContext Context context,
                    @DatabaseInfo String dbName,
                    @DatabaseInfo Integer version) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        tableCreateStatements(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }

    private void tableCreateStatements(SQLiteDatabase db) {
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + USER_TABLE_NAME + "("
                            + USER_COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + USER_COLUMN_USER_NAME + " VARCHAR(20), "
                            + USER_COLUMN_USER_SURNAME + " VARCHAR(50), "
                            + USER_COLUMN_USER_CONTACT_NUM + " VARCHAR(10)" + ")");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long insertUser(User user) throws Exception {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_COLUMN_USER_NAME, user.getName());
            contentValues.put(USER_COLUMN_USER_SURNAME, user.getSurname());
            contentValues.put(USER_COLUMN_USER_CONTACT_NUM, user.getPhone());
            return db.insert(USER_TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<User> getUser() {
        List<User> userList = new ArrayList<>();
        try {
            Cursor cursor = null;
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + "",null);
            if(cursor!=null && cursor.getCount()>0){
                cursor.moveToFirst();
                do {
                    User user = new User();
                    user.setId(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_ID)));
                    user.setName(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_NAME)));
                    user.setSurname(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_SURNAME)));
                    user.setPhone(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_CONTACT_NUM)));
                    userList.add(user);
                }while (cursor.moveToNext());
            }
            return userList;
        }catch (Exception e){
            e.printStackTrace();
            return userList;
        }
    }
}
