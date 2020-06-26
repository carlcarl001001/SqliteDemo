package com.example.carl.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Constans.database_name, null, Constans.VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //数据库 第一次创建时会调用
        MyLog.log("创建数据库。");
        //创建字段
        //sql: create table table_name(_id integer,name varchar,age integer,salary integer)
        String sql = "create table "+Constans.TABLE_NAME+"(_id integer,name varchar,age integer,salary integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //升级数据库时会调用
        MyLog.log("升级数据库");
        //sql: alter table table_name add phone integer
        String sql;
        switch (oldVersion){
            case 1:
                sql="alter table "+Constans.TABLE_NAME+" add phone integer,address varchar";
                db.execSQL(sql);
                break;
            case 2:
                sql="alter table "+Constans.TABLE_NAME+" add address varchar";
                db.execSQL(sql);
                break;
            case 3:
                break;


        }
    }

}



















