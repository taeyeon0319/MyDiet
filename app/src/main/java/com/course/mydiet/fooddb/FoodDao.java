package com.course.mydiet.fooddb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.course.mydiet.dietdb.Diet;
import com.course.mydiet.fooddb.Food;

import java.util.List;

@Dao
public interface FoodDao {
    @Query("SELECT * FROM Food")
    List<Food> getAll();

    @Query("SELECT * FROM Food WHERE connectdiet=:connectdiet")
    List<Food> loadAllByDietConnect(String connectdiet);

    @Insert
    void insertAll(Food... foods);

    @Delete
    void delete(Food food);

    @Query("SELECT SUM(kcal) FROM Food WHERE connectdiet LIKE '%' || :date || '%'")
    double loadKcalByDate(String date);

    @Query("SELECT SUM(kcal) FROM Food WHERE connectdiet=:datetitle")
    double loadKcalByDateTitle(String datetitle);
}