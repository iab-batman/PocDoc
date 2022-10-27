package com.example.pocdoc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    public static String DBNAME="mycontacts.db";
    public static int VERSION=1;

    public static String CREATE_CONTACTS_TABLE="CREATE TABLE "+
            MyContract.MyContacts.TABLE_NAME +" ( "+
            MyContract.MyContacts._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            MyContract.MyContacts._NAME +" TEXT, "+
            MyContract.MyContacts._PHNO +" TEXT NOT NULL "+
            " );";

    public static String DROPE_CONTACTS_TABLE="DROP TABLE IF EXISTS "+
            MyContract.MyContacts.TABLE_NAME;

    public MyDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROPE_CONTACTS_TABLE);
        onCreate(sqLiteDatabase);

    }
}
