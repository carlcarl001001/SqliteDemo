package com.example.carl.sqlitedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Dao {
    private final DatabaseHelper mHelper;

    public Dao(Context context) {
        this.mHelper = new DatabaseHelper(context);
    }
    public void insert(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "insert into "+Constans.TABLE_NAME + "(_id,name,age,salary,phone,address) values(?,?,?,?,?,?)";
        db.execSQL(sql,new Object[]{1,"Bile",60,1,110,"usa"});
        db.close();
    }
    public void delete(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        //String sql = "delete from "+Constans.TABLE_NAME + " where age = 60";
        String sql = "delete from "+Constans.TABLE_NAME + " where _id = 1";
        db.execSQL(sql);
        db.close();

    }
    public void update(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "update "+Constans.TABLE_NAME + " set salary = 2 where age = 60";
        db.execSQL(sql);
        db.close();
    }
    public void query(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "select * from "+Constans.TABLE_NAME ;
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int id_i = cursor.getColumnIndex("_id");
            int id = cursor.getInt(id_i);
            int name_i = cursor.getColumnIndex("name");
            String name = cursor.getString(name_i);
            int salary_i = cursor.getColumnIndex("salary");
            int salary = cursor.getInt(salary_i);
            MyLog.log("id:"+id+",name:"+name+",salary:"+salary);
        }
        //db.execSQL(sql);
        db.close();
    }
    //测试事务的安全性
    public void testTransaction(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            String sql = "update " + Constans.TABLE_NAME + " set salary = 1100+100 where name = 'abcde'";
            db.execSQL(sql);
            //int i = 10 / 0;//人为制造异常
            String sql2 = "update " + Constans.TABLE_NAME + " set salary = 2000-100 where name = 'Bile'";
            db.execSQL(sql2);
            db.setTransactionSuccessful();

        }catch (Exception e){
            MyLog.log("发生了异常。"+e.getMessage());
        }finally {
            db.endTransaction();
            db.close();
        }
    }
    //测试事务的高效性
    public void testTransaction2(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        long start = System.currentTimeMillis();
        for (int i=0;i<3000;i++){
            String sql = "insert into "+Constans.TABLE_NAME + "(_id,name,age,salary,phone,address) values(?,?,?,?,?,?)";
            db.execSQL(sql,new Object[]{1,"Bile",60,1,110,"usa"});
        }
        MyLog.log("normal use Time:"+(System.currentTimeMillis()-start));
        start = System.currentTimeMillis();
        db.beginTransaction();
        for (int i=0;i<3000;i++){
            String sql = "insert into "+Constans.TABLE_NAME + "(_id,name,age,salary,phone,address) values(?,?,?,?,?,?)";
            db.execSQL(sql,new Object[]{1,"Bile",60,1,110,"usa"});
        }
        db.endTransaction();
        MyLog.log("transaction use Time:"+(System.currentTimeMillis()-start));
        db.close();

    }
}










