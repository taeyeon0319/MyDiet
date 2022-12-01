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

    /*
    @ColumnInfo(name="image")
    public (이미지는 bitmap을 byte로 변환해서 해야함 - 구글링)

    @ColumnInfo(name="food")
    foreign key로 음식 데이터 만들 예정
    */

    @ColumnInfo(name="review")
    public String review;
}
