package com.example.pocdoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
/*import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;*/
import com.google.firebase.database.DatabaseReference;
/*import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;*/
import java.util.Objects;

public class AccountActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.navigation_account);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_pill_timer:
                        startActivity(new Intent(getApplicationContext(),Add.class));
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
                    case R.id.navigation_account:
                        return true;

                }
                return false;
            }


        });
    }
}

