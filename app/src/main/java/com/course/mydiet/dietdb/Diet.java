package com.course.mydiet.dietdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Diet {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="date")
    public String date;

    @ColumnInfo(name="title")
    public String title;

    @ColumnInfo(name="time")
    public String time;


    @ColumnInfo(name="image")
    public String image;

    @ColumnInfo(name="place")
    public String place;


    @ColumnInfo(name="review")
    public String review;
}
