package com.example.pocdoc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Add extends AppCompatActivity {
    ImageView dp;
    EditText name,num,address;
    Button save;
    BottomNavigationView bottomNavigationView;
    Uri dpp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dp=findViewById(R.id.dp);
        name=findViewById(R.id.name);
        num=findViewById(R.id.num);
        address=findViewById(R.id.address);
        save=findViewById(R.id.save);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper helper=new MyDBHelper(Add.this);
                SQLiteDatabase db=helper.getWritableDatabase();

                ContentValues cv=new ContentValues();
                cv.put(MyContract.MyContacts._NAME , name.getText()+"");
                cv.put(MyContract.MyContacts._PHNO , num.getText()+"");
                db.insert(MyContract.MyContacts.TABLE_NAME,null,cv);
                helper.close();
                finish();

            }
        });
    }


}