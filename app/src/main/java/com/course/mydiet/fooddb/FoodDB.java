package com.course.mydiet.fooddb;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.course.mydiet.dietdb.DietDao;
import com.course.mydiet.fooddb.*;

@Database(entities = {Food.class}, version = 1)
public abstract class FoodDB extends RoomDatabase {

    private static com.course.mydiet.fooddb.FoodDB INSTANCE = null;

    public abstract FoodDao foodDao();


    public static com.course.mydiet.fooddb.FoodDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    com.course.mydiet.fooddb.FoodDB.class, "food.db").build();

        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
