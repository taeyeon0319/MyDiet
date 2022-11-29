package com.course.mydiet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.course.mydiet.db.Diet;
import com.course.mydiet.db.DietDB;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
    public Button back;
    public Button remove;
    public String date, title, time, review;
    public TextView detailtitle, detailtime, detailreview;
    private Context dContext;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        back = findViewById(R.id.back2);
        remove = findViewById(R.id.remove);
        detailtitle = findViewById(R.id.diet_title);
        detailtime = findViewById(R.id.time2);
        detailreview = findViewById(R.id.dietreview);

        dContext = getApplicationContext();


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

        //삭제 버튼
        remove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
