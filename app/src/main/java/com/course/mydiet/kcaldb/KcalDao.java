package com.course.mydiet.kcaldb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.course.mydiet.dietdb.Diet;
import com.course.mydiet.fooddb.Food;

import java.util.List;

@Dao
public interface KcalDao {
    @Query("SELECT * FROM Kcal")
    List<Kcal> getAll();

    @Insert
    void insertAll(Kcal... kcals);

    @Query("SELECT foodkcal FROM Kcal WHERE foodkcalname=:foodname")
    double loadKcalByFood(String foodname);
}