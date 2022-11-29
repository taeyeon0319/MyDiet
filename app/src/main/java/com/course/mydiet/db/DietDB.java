package com.course.mydiet.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Diet.class}, version = 1)
public abstract class DietDB extends RoomDatabase {

    private static DietDB INSTANCE = null;

    public abstract DietDao dietDao();


    public static DietDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    DietDB.class, "diet.db").build();

        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
