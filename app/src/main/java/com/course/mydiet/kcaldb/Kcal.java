package com.course.mydiet.kcaldb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Kcal {
    @PrimaryKey(autoGenerate = true)
    public int kcalid;

    @ColumnInfo(name="foodkcalname")
    public String foodkcalname;

    @ColumnInfo(name="foodkcal")
    public double foodkcal;
}
