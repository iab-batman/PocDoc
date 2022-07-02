package com.example.pocdoc;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Location_class {

        public Double lat;
        public Double lon;

        public Location_class() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Location_class(Double lat, Double lon) {
            this.lat = lat;
            this.lon = lon;
        }
    public void writeLocation(DatabaseReference db, Double lat, Double lon) {
        Location_class Loc = new Location_class(lat, lon);

        db = FirebaseDatabase.getInstance("https://pocdoc-42948-default-rtdb.firebaseio.com/").getReference().child("Location");;
        db.child("Location").child("Latitude").setValue(Loc.lat);
        db.child("Location").child("Longitude").setValue(Loc.lon);
    }


}

