package com.course.mydiet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.course.mydiet.dietdb.Diet;
import com.course.mydiet.dietdb.DietAdapter;
import com.course.mydiet.dietdb.DietDB;
import com.course.mydiet.fooddb.Food;
import com.course.mydiet.fooddb.FoodAdapter;
import com.course.mydiet.fooddb.FoodDB;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    public Button back;
    public String date;
    public String title;
    public String time;
    public String review;
    public TextView detailtitle, detailtime, detailreview, calorie2;
    private Context dContext;
    private FoodDB foodDB = null;
    private Context fContext;

    private List<Food> foodList;
    private Context mContext = null;
    private FoodAdapter foodAdapter;
    private RecyclerView fRecyclerView;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        back = findViewById(R.id.back2);
        detailtitle = findViewById(R.id.diet_title);
        detailtime = findViewById(R.id.time2);
        detailreview = findViewById(R.id.dietreview);
        calorie2 = findViewById(R.id.calorie2);

        dContext = getApplicationContext();

        fRecyclerView = (RecyclerView) findViewById(R.id.fRecyclerView);
        mContext = getApplicationContext();
        foodAdapter = new FoodAdapter(foodList);

        foodDB = FoodDB.getInstance(this);
        fContext = getApplicationContext();

        Intent detailActivity = getIntent();

        if (detailActivity != null) {
            date = detailActivity.getStringExtra("date");
            title = detailActivity.getStringExtra("title");
            time= detailActivity.getStringExtra("time");
            review= detailActivity.getStringExtra("review");
        }

        int year = Integer.parseInt((date.split("\\.")[0]));
        int month = Integer.parseInt((date.split("\\.")[1]));
        int day = Integer.parseInt((date.split("\\.")[2]));
        detailtitle.setText(title);
        detailtime.setText(time);
        detailreview.setText(review);
        calorie2.setText("300");

        class InsertTotalKcalRunnable implements Runnable {
            @Override
            public void run() {
                Food food = new Food();
                double ff = FoodDB.getInstance(fContext).foodDao().loadKcalByDate(String.format("%d.%d.%d.-%s",year, month, day, detailtitle.getText().toString()));
                calorie2.setText(String.valueOf(ff)+"kcal");
            }
        }
        InsertTotalKcalRunnable inserttotalkcalRunnable = new InsertTotalKcalRunnable();
        Thread addThread = new Thread(inserttotalkcalRunnable);
        addThread.start();

        //food list
        class InsertRunnable implements Runnable {
            @Override
            public void run() {
                try {
                    foodList = FoodDB.getInstance(mContext).foodDao().loadAllByDietConnect(String.format("%d.%d.%d.-%s",year, month, day, detailtitle.getText().toString()));
                    foodAdapter = new FoodAdapter(foodList);
                    foodAdapter.notifyDataSetChanged();

                    fRecyclerView.setAdapter(foodAdapter);
                    LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
                    fRecyclerView.setLayoutManager(mLinearLayoutManager);
                }
                catch (Exception e) {

                }
            }
        }
        InsertRunnable insertRunnable = new InsertRunnable();
        Thread t = new Thread(insertRunnable);
        t.start();

        class DeleteRunnable implements Runnable{
            @Override
            public void run(){
                Diet diet = new Diet();
                DietDB.getInstance(dContext).dietDao().delete(diet);
            }
        }

        //뒤로 버튼
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), DietActivity.class);
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                startActivity(intent);
            }
        });
    }
}
