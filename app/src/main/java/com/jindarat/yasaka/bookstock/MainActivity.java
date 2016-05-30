package com.jindarat.yasaka.bookstock;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private String[][] strArray;
    private ListView listView;
    public static int listViewSelectId;
    public static String listViewSelectBookName,listViewSelectBookDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listViewSelectId = Integer.parseInt(strArray[0][position]);
                listViewSelectBookName = strArray[1][position];
                listViewSelectBookDetail = strArray[2][position];
            }
        });

        loadBook();
    }

    private void loadBook(){
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(DbOpenHelper.database_name,MODE_PRIVATE,null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM bookTable ORDER BY bName ASC", null);

        strArray = new String[3][cursor.getCount()];
        int i = 0;

        while(cursor.moveToNext()){
            String sqlId = cursor.getString(cursor.getColumnIndex("_id"));
            String sqlBookName = cursor.getString(cursor.getColumnIndex("bName"));
            String sqlBookDetail = cursor.getString(cursor.getColumnIndex("bDetail"));
            strArray[0][i] = sqlId;
            strArray[1][i] = sqlBookName;
            strArray[2][i] = sqlBookDetail;
            i++;
        }

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strArray[1]);
        listView.setAdapter(itemsAdapter);

    }

    public void  clickMainToAdd(View view){
        startActivity(new Intent(MainActivity.this,BookAdd.class));
    }

    public void  clickMainToEdit(View view){
        startActivity(new Intent(MainActivity.this,BookEdit.class));
    }

    public void  clickMainToDel(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("ต้องการลบใช่หรือไม่ ?").setPositiveButton("ใช่",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(DbOpenHelper.database_name,MODE_PRIVATE,null);
                sqLiteDatabase.execSQL("DELETE FROM bookTable WHERE _id = '"+listViewSelectId+"'");
                //myAlert.myDialogGreen(this,"ลบข้อมูล","ลบคำถามสำเร็จ");
                loadBook();
            }
        }).setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,int id) {

                dialog.cancel();
            }
        }).show();
        //startActivity(new Intent(MainActivity.this,BookAdd.class));
    }

}
