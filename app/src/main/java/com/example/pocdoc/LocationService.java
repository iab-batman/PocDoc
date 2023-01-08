package com.example.pocdoc;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;



public class LocationService extends Service {


   public static ArrayList<LatLng> locationArrayList = new ArrayList<LatLng>();

    FusedLocationProviderClient fusedLocationClient;
    LocationRequest locationRequest;
    public static final int UPDATE_INTERVAL = 30000;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference root =firebaseDatabase.getReference().child("location");
    LocationCallback locationCallback;

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }
//    private void initArea() {
//
//
//    }

//    protected void createLocationRequest() {
//        LocationRequest locationRequest = LocationRequest.create();
//        locationRequest.setInterval(30000);
////        locationRequest.setFastestInterval(5000);
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//    }

    @Override
    public void onCreate() {
        super.onCreate();

        new Notification();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) createNotificationChanel() ;
        else startForeground(
                1,
                new Notification()
        );

        locationRequest = LocationRequest.create();
//        locationRequest.setFastestInterval(3000);
        locationRequest.setInterval(UPDATE_INTERVAL);
//        locationRequest.setMaxWaitTime(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                Location location =  locationResult.getLastLocation();





                Toast.makeText(getApplicationContext(),
                      "Lat: "+Double.toString(location.getLatitude()) + '\n' +
                        "Long: " + Double.toString(location.getLongitude()), Toast.LENGTH_SHORT).show();

                locationArrayList.add(new LatLng(location.getLatitude(), location.getLongitude()));
                double a = location.getLatitude();
                double b = location.getLongitude();

//                ArrayList<Object> dangerousArea = new ArrayList<>();
//                dangerousArea.add(new LatLng(26.720126,94.156374));
//                dangerousArea.add(new LatLng(26.722673,94.195730));
//                dangerousArea.add(new LatLng(26.735694,94.223904));
//
//                locationArrayList.retainAll(dangerousArea);

//                Location.distanceBetween(location.getLatitude(),location.getLongitude(),33.6563001,73.0130935,result);

                Location locationA = new Location("point A");

                locationA.setLatitude(a);
                locationA.setLongitude(b);

                Location locationB = new Location("point B");

                locationB.setLatitude(33.6563001);
                locationB.setLongitude(73.0130935);

                float[] results = new float[1];
                Location.distanceBetween(a,b,33.6563001,73.0130935,results);
                float distance = results[0];

                if (distance<500){
                    Toast.makeText(LocationService.this, "You are in danger Zone", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LocationService.this, "You are not in danger Zone", Toast.LENGTH_SHORT).show();

                }

                //Toast.makeText(LocationService.this,String.valueOf(distance), Toast.LENGTH_SHORT).show();


                FirebaseDatabase.getInstance().getReference("Current location").setValue(locationArrayList).addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LocationService.this, "location saved", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(LocationService.this, "location Not saved", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

               /* for (Location location : locationResult.getLocations()) {
                  location
                }*/
            }
        };
        startLocationUpdates();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChanel() {
        String notificationChannelId = "Location channel id";
        String channelName = "Background Service";

        NotificationChannel chan = new NotificationChannel(
                notificationChannelId,
                channelName,
                NotificationManager.IMPORTANCE_NONE
        );
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        NotificationManager manager = getSystemService(NotificationManager.class);

        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder =
               new NotificationCompat.Builder(this, notificationChannelId);

        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle("Location updates:")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
