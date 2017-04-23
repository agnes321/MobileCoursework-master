//package com.example.a1408876.films;
//
///**
// * Created by Lenovo on 2017-04-22.
// */
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import java.util.ArrayList;
//
//public class DatabaseUtility {
//    SQLiteDatabase myDB;
//
//    public DatabaseUtility(Context con) {
//        DatabaseHelper db = new DatabaseHelper (con);
//        myDB = db.getWritableDatabase();
//
//    }
//    public ArrayList<String> getSurnames()
//    {
//        ArrayList<String> records = new ArrayList<String>();
//        Cursor cur = null;
//        String[] rows = new String[] { "Title", "Overview", "ReleaseDate", "OriginalTitle", "OriginalLanguage", "Popularity", "VoteAverage", "VoteCount", "MyNote" };
//
//
//        try {
//            cur = myDB.query (DatabaseHelper.DB_NAME, rows, null, null,
//                    null, null, null);
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        while (cur.moveToNext()) {
//            records.add (cur.getString(0));
//        }
//
//        return records;
//    }
//}
//
//
//
