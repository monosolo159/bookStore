package com.jindarat.yasaka.bookstock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class BookAdd extends AppCompatActivity {

    private EditText edtName, edtDetail;;
    private String strName, strDetail;
    private DbSQLite dbSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add);

        edtName = (EditText) findViewById(R.id.edtDesignName);
        edtDetail = (EditText) findViewById(R.id.edtDesignDetail);
    }

    public void  clickBookAddToMain(View view){
        startActivity(new Intent(BookAdd.this,MainActivity.class));
    }

    public void  clickBookAddReset(View view){

    }

    public void clickBookAddToDB(View view){

        strName = edtName.getText().toString().trim();
        strDetail = edtDetail.getText().toString().trim();

        MyAlert myAlert = new MyAlert();
        if(checkSpace()){
            myAlert.myDialog(this,"มีช่องว่าง","กรุณาระบุข้อมูลทุกช่อง");
        }else{
            dbSQLite = new DbSQLite(this);
            dbSQLite.addNewQuestion(strName,strDetail);
            myAlert.myDialog(this,"สถานะบันทึกข้อมูล","บันทึกข้อมูลสำเร็จ");
            editextNull();
        }
        startActivity(new Intent(BookAdd.this,MainActivity.class));

    }

    private void editextNull() {
        edtName.setText("");
        edtDetail.setText("");
    }

    private boolean checkSpace() {
        return strName.equals("") || strDetail.equals("");
    }

    public void cilckBookAddReset(View view){
        editextNull();
    }


}
