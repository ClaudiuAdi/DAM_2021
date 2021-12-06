package com.example.eventbrite;

import android.content.Context;

import androidx.room.Room;

public class DataBaseAccess {
    private Database database;

    private DataBaseAccess(Context context) {
        database = Room.databaseBuilder(context, Database.class, "dbEventbrite").build();
    }

    private static DataBaseAccess instance;

    public static DataBaseAccess getInstance(Context c) {
        if (instance == null) {
            instance = new DataBaseAccess(c);
        }
        return instance;
    }

    public Database getDatabase() {
        return database;
    }
}