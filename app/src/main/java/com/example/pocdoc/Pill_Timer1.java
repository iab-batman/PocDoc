package com.example.pocdoc;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Pill_Timer1 extends AppCompatActivity {
    RecyclerView rv;
    MyAdapter adapter;
    BottomNavigationView bottomNavigationView;

    ImageView add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_timer1);
        rv=findViewById(R.id.rv);
//        bottomNavigationView.setSelectedItemId(R.id.navigation_pill_timer);
//        bottomNavigationView = findViewById(R.id.bottom_navigator);
        add=findViewById(R.id.add);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                switch (item.getItemId()) {
//                    case R.id.navigation_home:
//                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//
//                    case R.id.navigation_pill_timer:
//                        return true;
//                    case R.id.navigation_covid_tracker:
//                        startActivity(new Intent(getApplicationContext(), CovidExposureTracker.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.navigation_nurse_bot:
//                        startActivity(new Intent(getApplicationContext(), Nurse_bot.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.navigation_account:
//                        startActivity(new Intent(getApplicationContext(),AccountActivity.class));
//                        overridePendingTransition(0,0);
//                        return true;
//
//                }
//                return false;
//            }
//        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Pill_Timer1.this,Add.class);
                startActivity(intent);
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter=new MyAdapter(getData(),Pill_Timer1.this);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(Pill_Timer1.this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
    }

    public List<MyModel> getData(){
        List<MyModel> lss=new ArrayList<>();
        MyDBHelper helper=new MyDBHelper(Pill_Timer1.this);
        SQLiteDatabase db=helper.getReadableDatabase();
        String[] cols={MyContract.MyContacts._ID,
                MyContract.MyContacts._NAME,
                MyContract.MyContacts._PHNO};
        Cursor c=db.query(
                MyContract.MyContacts.TABLE_NAME,
                cols,
                null,
                null,
                null,
                null,
                MyContract.MyContacts._NAME+" ASC"
        );
        while (c.moveToNext())
        {
            lss.add(
                    new MyModel(
                            c.getString(c.getColumnIndex(MyContract.MyContacts._NAME)),
                            c.getString(c.getColumnIndex(MyContract.MyContacts._PHNO)),
                            ""
                    )
            );
        }
        db.close();
        helper.close();
        return lss;

    }


}