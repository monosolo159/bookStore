package com.jindarat.yasaka.bookstock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by NUI on 30/5/2559.
 */
public class DbOpenHelper extends SQLiteOpenHelper {
    public static final String database_name = "BookStock.db";
    private static final int database_version = 1;

    private static final String create_quiz_table = "create table bookTable("+
            "_id integer primary key, "+
            "bName text, "+
            "bDetail text);";

    public DbOpenHelper(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_quiz_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
