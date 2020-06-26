package com.example.carl.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Dao2 {
    private final DatabaseHelper mHelper;

    public Dao2(Context context) {
        this.mHelper = new DatabaseHelper(context);
    }
    public void insert(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
//        String sql = "insert into "+Constans.TABLE_NAME + "(_id,name,age,salary,phone,address) values(?,?,?,?,?,?)";
////        db.execSQL(sql,new Object[]{1,"Bile",60,1,110,"usa"});
        ContentValues values = new ContentValues();
        //添加数据
        values.put("_id",1);
        values.put("name","Bile");
        values.put("salary",1);
        values.put("phone",1290);
        values.put("address","USA");
        db.insert(Constans.TABLE_NAME,null,values);
        db.close();
    }
    public void delete(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
//        String sql = "delete from "+Constans.TABLE_NAME + " where age = 60";
//        db.execSQL(sql);
        int result = db.delete(Constans.TABLE_NAME,null,null);
        MyLog.log("delete_result:"+result);
        db.close();

    }
    public void update(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        //String sql = "update "+Constans.TABLE_NAME + " set salary = 2 where age = 60";
        //db.execSQL(sql);
        ContentValues values = new ContentValues();
        values.put("phone",123456789);
        db.update(Constans.TABLE_NAME,values,null,null);
        db.close();



    }
    public void query(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
//        String sql = "select * from "+Constans.TABLE_NAME ;
//        Cursor cursor = db.rawQuery(sql,null);
//        while (cursor.moveToNext()){
//            int index = cursor.getColumnIndex("name");
//            String name = cursor.getString(index);
//            MyLog.log("name:"+name);
//
//        }
//        db.execSQL(sql);
        Cursor cursor = db.query(Constans.TABLE_NAME,null,null,null,
                null,null,null);
        while (cursor.moveToNext()){
            int id_i = cursor.getColumnIndex("_id");
            int id = cursor.getInt(id_i);
            int name_i = cursor.getColumnIndex("name");
            String name = cursor.getString(name_i);
            MyLog.log("id:"+id+",name:"+name);
        }
        cursor.close();
        db.close();
    }
}










