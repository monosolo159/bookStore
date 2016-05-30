package com.jindarat.yasaka.bookstock;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class BookEdit extends AppCompatActivity {
    private EditText eBook, eDetail;
    private String strBook, strDetail;

    private DbSQLite dbSQLite;
    private MyAlert myAlert = new MyAlert();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_edit);

        eBook = (EditText) findViewById(R.id.edtEditBookName);
        eDetail = (EditText) findViewById(R.id.edtEditBookDetail);
        setValue();
    }

    private void setValue() {
        eBook.setText(MainActivity.listViewSelectBookName);
        eDetail.setText(MainActivity.listViewSelectBookDetail);
    }

    public void  clickBookEditToMain(View view){
        startActivity(new Intent(BookEdit.this,MainActivity.class));
    }

    private boolean checkSpace() {
        return strBook.equals("")||strDetail.equals("");
    }

    public void cilckBookUpdate(View view){

        strBook = eBook.getText().toString().trim();
        strDetail = eDetail.getText().toString().trim();

        if(checkSpace()){
            myAlert.myDialog(this,"มีช่องว่าง","กรุณาระบุข้อมูลทุกช่อง");
        }else{

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("ต้องการแก้ไขใช่หรือไม่ ?").setPositiveButton("ใช่",  new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(DbOpenHelper.database_name,MODE_PRIVATE,null);
                    sqLiteDatabase.execSQL("UPDATE bookTable SET bName='"+strBook+"', bDetail='"+strDetail+"' WHERE _id='"+MainActivity.listViewSelectId+"'");
                    startActivity(new Intent(BookEdit.this,MainActivity.class));
                }
            }).setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog,int id) {
                    dialog.cancel();
                }
            }).show();
        }
    }
}
