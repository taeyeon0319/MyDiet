package com.course.mydiet.dietdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface DietDao {
    @Query("SELECT * FROM Diet")
    List<Diet> getAll();

    @Query("SELECT * FROM Diet WHERE id IN (:dateIds)")
    List<Diet> loadAllByIds(int[] dateIds);

    @Insert
    void insertAll(Diet... diets);

    @Delete
    void delete(Diet diet);

    @Query("SELECT * FROM Diet WHERE date=:dietdate")
    List<Diet> loadAllByDate(String dietdate);

}
