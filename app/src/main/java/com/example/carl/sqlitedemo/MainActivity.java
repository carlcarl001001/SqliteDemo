package com.example.carl.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建数据库
        DatabaseHelper helper = new DatabaseHelper(this);
        helper.getWritableDatabase();
        Dao dao = new Dao(this);
        //dao.insert();
        //dao.delete();
        dao.testTransaction2();
        //dao.query();

//        Dao2 dao2= new Dao2(this);
//        dao2.insert();
////        dao2.delete();
//        dao2.query();

    }
}
