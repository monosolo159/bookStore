package com.jindarat.yasaka.bookstock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BookEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_edit);
    }

    public void  clickBookEditToMain(View view){
        startActivity(new Intent(BookEdit.this,MainActivity.class));
    }

}
