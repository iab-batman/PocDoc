package com.example.pocdoc;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;


public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread thread=new Thread(){
        public void run(){
            try {
                sleep(4000);

            }
            catch(Exception e)
            {

            }
            finally {
                Intent intent=new Intent(Splash_Activity.this,MainActivity.class);
                startActivity(intent);
            }
        }
    };thread.start();
}}