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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.PublicKey;

public class Add extends AppCompatActivity {
    ImageView dp;
    EditText name,num,address;
    Button save;
    BottomNavigationView bottomNavigationView;
    Uri dpp;
    // for first drop down menu
    Spinner timedropdown;
    String time;
    ArrayAdapter<CharSequence> adapter;
    //for second drop down menu
    Spinner alarmdropdown;
    String alarm;
    ArrayAdapter<CharSequence> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dp=findViewById(R.id.dp);
        name=findViewById(R.id.name);
        num=findViewById(R.id.num);
        address=findViewById(R.id.address);
        save=findViewById(R.id.save);
        //This is for first drop down menu
        adapter=ArrayAdapter.createFromResource(this, R.array.TimeDropDownMenu, android.R.layout.simple_spinner_item);
        timedropdown = findViewById(R.id.dropdowntime);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        timedropdown.setAdapter(adapter);
        //This is for the second drop down menu
        adapter1=ArrayAdapter.createFromResource(this,R.array.AlarmRepeatDropDown,android.R.layout.simple_spinner_item);
        alarmdropdown = findViewById(R.id.dropdownalarmrepeat);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);

        alarmdropdown.setAdapter(adapter1);

        //Saving the info of the dropdown menus




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper helper=new MyDBHelper(Add.this);
                SQLiteDatabase db=helper.getWritableDatabase();

                time = timedropdown.getSelectedItem().toString();
                alarm = alarmdropdown.getSelectedItem().toString();
                ContentValues cv=new ContentValues();
                cv.put(MyContract.MyContacts._NAME , name.getText()+"");
               // cv.put(MyContract.MyContacts._PHNO , num.getText()+"");
                cv.put(MyContract.MyContacts._PHNO , alarm+"");
                db.insert(MyContract.MyContacts.TABLE_NAME,null,cv);
                helper.close();
                finish();

            }
        });
    }


}