package com.course.mydiet.kcaldb;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Kcal.class}, version = 1)
public abstract class KcalDB extends RoomDatabase {

    private static com.course.mydiet.kcaldb.KcalDB INSTANCE = null;

    public abstract KcalDao kcalDao();


    public static com.course.mydiet.kcaldb.KcalDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    com.course.mydiet.kcaldb.KcalDB.class, "kcal.db").build();

        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
