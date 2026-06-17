package com.peggy.happyvienna;

import android.app.Application;
import android.util.Log;
import androidx.room.Room;
import com.peggy.happyvienna.data.AppDatabase;

public class HappyViennaApp extends Application {

    private static final String TAG = "HappyViennaApp";
    private static final String DB_NAME = "happy-vienna-room.db";

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDatabase();
    }

    private void initializeDatabase() {
        try {
            database = Room.databaseBuilder(this, AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
            Log.d(TAG, "Room Database initialized successfully.");
        } catch (Exception e) {
            Log.e(TAG, "Critical Failure: Database initialization aborted.", e);
        }
    }

    public AppDatabase getDatabase() {
        return database;
    }
}