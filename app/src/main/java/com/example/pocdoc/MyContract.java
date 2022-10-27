package com.example.pocdoc;

import android.provider.BaseColumns;

public class MyContract {
    private MyContract(){}

    public static class MyContacts implements BaseColumns {
        public static String TABLE_NAME="cotacts";
        public static String _NAME="name";
        public static String _PHNO="phno";

    }
}
