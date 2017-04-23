package com.example.a1408876.films;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;


/**
 * Created by Lenovo on 2017-04-22.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "WATCHLIST_DATABASE";
    public static final int DB_VERSION = 1;
    public static final String COL0 = "id";


    public DatabaseHelper(Context myContext) {
        super (myContext, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase myDB) {
        String sql;
        //ContentValues records;
        sql = "CREATE TABLE " + DB_NAME +
                " (id INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL, Title TEXT, Overview TEXT, ReleaseDate TEXT, OriginalTitle TEXT, OriginalLanguage TEXT, Popularity TEXT, VoteAverage TEXT, VoteCount TEXT, MyNote TEXT);";
        myDB.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addData(String title, String overview, String rd, String ot, String ol, String p, String va, String vc, String n){
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues records = new ContentValues();
        long result;
        records.put("Title", title);
        records.put("Overview", overview);
        records.put("ReleaseDate", rd);
        records.put("OriginalTitle", ot);
        records.put("OriginalLanguage", ol);
        records.put("Popularity", p);
        records.put("VoteAverage", va);
        records.put("VoteCount", vc);
        records.put("MyNote", n);

        Log.d("Database helper","Adding to the database");
        result = myDb.insert(DB_NAME, null, records);

        if(result == -1){
            return false; //data inserted incorrectly
        }
        else{
            return true; //data inserted correctly
        }
    }

    public Cursor getData(){
        SQLiteDatabase myDb = this.getWritableDatabase();
        String query = "SELECT * FROM " + DB_NAME;
        Cursor data = myDb.rawQuery(query, null);
        return data;
    }

    public boolean checkIfDataExists(String fieldValue){
        SQLiteDatabase myDb = this.getReadableDatabase();
        String Query = "Select * from " + DB_NAME + " where " + "Overview" + " = ?;";
        Cursor cursor = myDb.rawQuery(Query, new String[] {fieldValue});
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

}
