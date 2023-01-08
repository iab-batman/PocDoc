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
import android.widget.ImageView;
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


public class HomeActivity extends AppCompatActivity {
    private DatabaseReference databaseReference = null;
    private Location mLocation;
    private LocationListener locationListener;
    private LocationManager locationManager;

    ImageView chatbot,timer,tracker;
    private Toolbar toolbar;




    private final long MIN_TIME = 1000;
    private final long MIN_DIST = 5;



    Button Logout_btn, Map_Btn ;
    ClipData.Item menu_nav, account;
    FirebaseAuth mFirebaseAuth;
    BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        timer=findViewById(R.id.pilltimerbtn);
        tracker=findViewById(R.id.tracker);
        chatbot=findViewById(R.id.chatBot);
        chatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this,Nurse_bot.class);
                startActivity(i);
            }
        });
        tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this,CovidExposureTracker.class);
                startActivity(i);
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this,Pill_Timer1.class);
                startActivity(i);
            }
        });



       //Logout_btn = findViewById(R.id.logout_btn);
        //Logout_btn.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View view) {
              //  FirebaseAuth.getInstance().signOut();

                //Intent InToMain = new Intent(HomeActivity.this,MainActivity.class);
                //startActivity(InToMain);
            //}

        //});
        /* Map_Btn = findViewById(R.id.map_btn);
        Map_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent InToMap = new Intent(HomeActivity.this, MapsActivity.class);
                startActivity(InToMap);
            }
        });
*/




        ActivityCompat.requestPermissions(HomeActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);



//                double longitude = mLocation.getLongitude();
//                double latitude = mLocation.getLatitude();
//                lc.writeLocation(databaseReference,latitude, longitude);

    }
}