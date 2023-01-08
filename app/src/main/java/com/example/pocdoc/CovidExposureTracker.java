package com.example.pocdoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CovidExposureTracker extends AppCompatActivity {

    private static int MY_FINE_LOCATION_REQUEST = 99;
    private static int MY_BACKGROUND_LOCATION_REQUEST = 100;

    LocationService mLocationService = new LocationService();
    Intent mServiceIntent;
    TextView textView;
    BottomNavigationView bottomNavigationView;

    Button startServiceBtn, stopServiceBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_exposure_tracker);

        startServiceBtn = findViewById(R.id.start_service_btn);
        stopServiceBtn = findViewById(R.id.stop_service_btn);
        textView=findViewById(R.id.coronaidstatus);
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        return true;
                    case R.id.navigation_pill_timer:
                        startActivity(new Intent(getApplicationContext(),Pill_Timer1.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_covid_tracker:
                        startActivity(new Intent(getApplicationContext(),CovidExposureTracker.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_nurse_bot:
                        startActivity(new Intent(getApplicationContext(),Nurse_bot.class));
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }


        });


        /**************/

        startServiceBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(CovidExposureTracker.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                        if (ActivityCompat.checkSelfPermission(CovidExposureTracker.this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                                != PackageManager.PERMISSION_GRANTED) {


                            AlertDialog alertDialog = new AlertDialog.Builder(CovidExposureTracker.this).create();
                            alertDialog.setTitle("Background permission");
                            alertDialog.setMessage("Background_location_permission_message");

                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Start service anyway",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            starServiceFunc();
                                            dialog.dismiss();
                                        }
                                    });

                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Grant background Permission",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestBackgroundLocationPermission();
                                            dialog.dismiss();
                                        }
                                    });

                            alertDialog.show();


                        }else if (ActivityCompat.checkSelfPermission(CovidExposureTracker.this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                                == PackageManager.PERMISSION_GRANTED){
                            starServiceFunc();
                        }
                    }else{
                        starServiceFunc();
                    }

                }else if (ActivityCompat.checkSelfPermission(CovidExposureTracker.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.shouldShowRequestPermissionRationale(CovidExposureTracker.this, Manifest.permission.ACCESS_FINE_LOCATION)) {


                        AlertDialog alertDialog = new AlertDialog.Builder(CovidExposureTracker.this).create();
                        alertDialog.setTitle("ACCESS_FINE_LOCATION");
                        alertDialog.setMessage("Location permission required");

                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestFineLocationPermission();
                                        dialog.dismiss();
                                    }
                                });


                        alertDialog.show();

                    } else {
                        requestFineLocationPermission();
                    }
                }

            }
        });

        stopServiceBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopServiceFunc();
            }
        });


        /***********/

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_LONG).show();

        if ( requestCode == MY_FINE_LOCATION_REQUEST){

            if (grantResults.length !=0 /*grantResults.isNotEmpty()*/ && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    requestBackgroundLocationPermission();
                }

            } else {
                Toast.makeText(this, "ACCESS_FINE_LOCATION permission denied", Toast.LENGTH_LONG).show();
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                 /*   startActivity(
                            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", this.getPackageName(), null),),);*/

                    startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:com.example.pocdoc")
                    ));

                }
            }
            return;

        }else if (requestCode == MY_BACKGROUND_LOCATION_REQUEST){

            if (grantResults.length!=0 /*grantResults.isNotEmpty()*/ && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Background location Permission Granted", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Background location permission denied", Toast.LENGTH_LONG).show();
            }
            return;
        }

    }

    private void starServiceFunc(){
        mLocationService = new LocationService();
        mServiceIntent = new Intent(this, mLocationService.getClass());
        if (!Util.isMyServiceRunning(mLocationService.getClass(), this)) {
            startService(mServiceIntent);
            Toast.makeText(this, "Service start successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"Service already running", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopServiceFunc(){
        mLocationService = new LocationService();
        mServiceIntent = new Intent(this, mLocationService.getClass());
        if (Util.isMyServiceRunning(mLocationService.getClass(), this)) {
            stopService(mServiceIntent);
            Toast.makeText(this, "Service stopped!!", Toast.LENGTH_SHORT).show();
            //saveLocation(); // explore it by your self
        } else {
            Toast.makeText(this, "Service is already stopped!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void requestBackgroundLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                MY_BACKGROUND_LOCATION_REQUEST);
    }

    private void requestFineLocationPermission() {
        ActivityCompat.requestPermissions(this,  new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, MY_FINE_LOCATION_REQUEST);
    }


}
