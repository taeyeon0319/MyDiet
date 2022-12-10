package com.course.mydiet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.course.mydiet.dietdb.DietDB;
import com.course.mydiet.fooddb.Food;
import com.course.mydiet.fooddb.FoodDB;
import com.course.mydiet.kcaldb.Kcal;
import com.course.mydiet.kcaldb.KcalDB;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class MainActivity extends AppCompatActivity {
    //데이터베이스
    private DietDB dietDB = null;
    private Context dContext;

    private FoodDB foodDB = null;
    private Context fContext;

    public CalendarView calendarView;
    public TextView yymmdd,calendartitle, calorie;
    public Button add_btn, detail_btn;

    public View purple;

    private KcalDB kcalDB = null;
    private Context kContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView=findViewById(R.id.calendarView);
        yymmdd=findViewById(R.id.yymmdd);
        calendartitle=findViewById(R.id.calendartitle);
        add_btn = findViewById(R.id.addbutton);
        detail_btn = findViewById(R.id.detailbutton);
        calorie = findViewById(R.id.calorie);
        purple = findViewById(R.id.view);

        //DB 생성
        dietDB = DietDB.getInstance(this);
        dContext = getApplicationContext();

        foodDB = FoodDB.getInstance(this);
        fContext = getApplicationContext();

        //칼로리 DB
        kcalDB = KcalDB.getInstance(this);
        kContext = getApplicationContext();


        class InsertKcalRunnable implements Runnable {
            @Override
            public void run() {

                Kcal kcal = new Kcal();
                /*//<초기설정>
                kcal.foodkcalname = "사과";
                kcal.foodkcal= 52;
                KcalDB.getInstance(kContext).kcalDao().insertAll(kcal);
                kcal.foodkcalname = "바나나";
                kcal.foodkcal= 93;
                KcalDB.getInstance(kContext).kcalDao().insertAll(kcal);
                kcal.foodkcalname = "떡볶이";
                kcal.foodkcal= 335;
                KcalDB.getInstance(kContext).kcalDao().insertAll(kcal);
                kcal.foodkcalname = "치킨";
                kcal.foodkcal= 249;
                KcalDB.getInstance(kContext).kcalDao().insertAll(kcal);
                kcal.foodkcalname = "피자";
                kcal.foodkcal= 255;
                KcalDB.getInstance(kContext).kcalDao().insertAll(kcal);
                kcal.foodkcalname = "초코우유";
                kcal.foodkcal= 135;
                KcalDB.getInstance(kContext).kcalDao().insertAll(kcal);
                kcal.foodkcalname = "콜라";
                kcal.foodkcal= 92;
                KcalDB.getInstance(kContext).kcalDao().insertAll(kcal);
                kcal.foodkcalname = "아메리카노";
                kcal.foodkcal= 4;
                KcalDB.getInstance(kContext).kcalDao().insertAll(kcal);
                kcal.foodkcalname = "김치찌개";
                kcal.foodkcal= 128;
                KcalDB.getInstance(kContext).kcalDao().insertAll(kcal);
                kcal.foodkcalname = "김치볶음밥";
                kcal.foodkcal= 446;
                KcalDB.getInstance(kContext).kcalDao().insertAll(kcal);
                kcal.foodkcalname = "삼겹살";
                kcal.foodkcal= 661;
                KcalDB.getInstance(kContext).kcalDao().insertAll(kcal);
                kcal.foodkcalname = "짜장면";
                kcal.foodkcal= 785;
                KcalDB.getInstance(kContext).kcalDao().insertAll(kcal);
                */
                KcalDB.getInstance(kContext).kcalDao().getAll();
            }
        }
        InsertKcalRunnable insertkcalRunnable = new InsertKcalRunnable();
        Thread addThread = new Thread(insertkcalRunnable);
        addThread.start();

        //날짜 선택
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                add_btn.setVisibility(View.VISIBLE);
                detail_btn.setVisibility(View.VISIBLE);
                purple.setVisibility(View.VISIBLE);
                add_btn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                        intent.putExtra("year", year);
                        intent.putExtra("month", month+1);
                        intent.putExtra("day", dayOfMonth);
                        startActivity(intent);
                    }
                });

                detail_btn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Intent intent = new Intent(getApplicationContext(), DietActivity.class);
                        intent.putExtra("year", year);
                        intent.putExtra("month", month+1);
                        intent.putExtra("day", dayOfMonth);
                        startActivity(intent);
                    }
                });
                class InsertKcalFoodRunnable implements Runnable {
                    @Override
                    public void run() {
                        Food food = new Food();
                        double ff = FoodDB.getInstance(fContext).foodDao().loadKcalByDate(String.format("%d.%d.%d.",year,month+1,dayOfMonth));
                        calorie.setVisibility(View.VISIBLE);
                        calorie.setText(String.valueOf(ff)+"kcal");
                    }
                }
                InsertKcalFoodRunnable insertkcalfoodRunnable = new InsertKcalFoodRunnable();
                Thread addThread = new Thread(insertkcalfoodRunnable);
                addThread.start();

                yymmdd.setVisibility(View.VISIBLE);
                yymmdd.setText(String.format("%d년 %d월 %d일 총 칼로리",year,month+1,dayOfMonth));
            }
        });
    }
/*
    public void readExcel(){
        //칼로리 DB저장
        AssetManager assetManager = getResources().getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("foodkcal.xlsx");
            Workbook wb = Workbook.getWorkbook(inputStream);

            if(wb!=null){
                Sheet sheet = wb.getSheet(0);
                if(sheet != null){
                    int colTotal = sheet.getColumns();
                    int rowIndexStart = 1;
                    int rowTotal = sheet.getColumn(colTotal-1).length;

                    StringBuilder sb;
                    for(int row = rowIndexStart; row < rowTotal; row++){
                        sb = new StringBuilder();

                        //col:칼럼 순서, contents:데이터값
                        for(int col = 0; col<colTotal;col++){
                            String contents = sheet.getCell(col, row).getContents();
                            Log.d("Main", col + "번째: "+contents);
                        }
                    }
                }
            }
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
    }*/
}