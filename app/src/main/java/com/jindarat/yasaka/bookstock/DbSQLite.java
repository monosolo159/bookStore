package com.jindarat.yasaka.bookstock;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by NUI on 30/5/2559.
 */
public class DbSQLite {
    private DbOpenHelper dbOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String quiz_table = "bookTable";
    public static final String column_id = "_id";
    public static final String column_book = "bName";
    public static final String column_detail = "bDetail";

    public DbSQLite(Context context){
        dbOpenHelper = new DbOpenHelper(context);
        sqLiteDatabase = dbOpenHelper.getWritableDatabase();
    }

    public long addNewQuestion(String strBName, String strBDetail){
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_book,strBName);
        contentValues.put(column_detail,strBDetail);

        return sqLiteDatabase.insert(quiz_table,null,contentValues);
    }
}
