package com.jindarat.yasaka.bookstock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void  clickMainToAdd(View view){
        startActivity(new Intent(MainActivity.this,BookAdd.class));
    }

    public void  clickMainToEdit(View view){
        startActivity(new Intent(MainActivity.this,BookEdit.class));
    }

    public void  clickMainToDel(View view){
        //startActivity(new Intent(MainActivity.this,BookAdd.class));
    }

}
