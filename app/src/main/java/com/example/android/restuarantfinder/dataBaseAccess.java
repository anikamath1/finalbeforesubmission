package com.example.android.restuarantfinder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class dataBaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static dataBaseAccess instance;


    //object creation
    private dataBaseAccess(Context context){
        this.openHelper = new dataBaseOpenHelper(context);
    }

    //return instance of the database
    public static dataBaseAccess getInstance(Context context){
        if(instance == null){
            instance = new dataBaseAccess(context);
        }
        return instance;
    }

    //to open database
    public void open(){
        this.db = openHelper.getReadableDatabase();


    }

    public void close(){
        if(db != null){
            this.db.close();
        }
    }

    //create method to query and return result

    public String getRestName(String name) {

        String[] selectionArgs = {name};
        Cursor cursor = db.rawQuery("select * from restDetails where restName like ?", selectionArgs);

        String rname = "";
        if(cursor.moveToFirst()){
            rname = cursor.getString(1);
            Log.d("name of resto",rname);
        }
        cursor.close();

        return rname;

    }

    public ArrayList<allMenuMao> getStartersMao(String itemType){
        ArrayList<allMenuMao> list = new ArrayList<>();
        String[] selectionArgs = {itemType};
        allMenuMao menuMao;
        Cursor cursor = db.rawQuery("select * from menuMao where itemType like ?", selectionArgs);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            menuMao = new allMenuMao(cursor.getString(2), cursor.getInt(3));
            list.add(menuMao);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    public void openForWriting(){
        this.db = openHelper.getWritableDatabase();
    }

    public ArrayList<allMenuMao> getDrinksMao(String itemType){
        ArrayList<allMenuMao> list = new ArrayList<>();
        allMenuMao menuMao;
        String[] selectionArgs = {itemType};
        Cursor cursor = db.rawQuery("select * from menuMao where itemType like ?", selectionArgs);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            menuMao = new allMenuMao(cursor.getString(2), cursor.getInt(3));
            list.add(menuMao);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public ArrayList<allMenuMao> getAllMenuMao(){
        ArrayList<allMenuMao> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from menuMao", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(new allMenuMao(cursor.getString(2), cursor.getInt(3)));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public void insert(String restname)
    {
        String str = "INSERT INTO restDetails(restID,restName,restAddress) VALUES(?,?,?)";
        SQLiteStatement stmt = db.compileStatement(str);
        stmt.bindLong(1, 9);
        stmt.bindString(2,restname);
        stmt.bindString(3, "andheri");
        long i = stmt.executeInsert();
    }


}


