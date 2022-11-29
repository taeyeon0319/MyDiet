package com.course.mydiet.db;

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
}
