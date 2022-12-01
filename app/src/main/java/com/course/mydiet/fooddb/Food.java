package com.course.mydiet.fooddb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Food {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="foodname")
    public String foodname;

    @ColumnInfo(name="amount")
    public int amount;

    @ColumnInfo(name="connectdiet")
    public String connectdiet;

}
