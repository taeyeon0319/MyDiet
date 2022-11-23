package com.course.mydiet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class DietActivity extends AppCompatActivity{

    public Button btc;
    public Button plus;
    public ConstraintLayout example;
    public TextView textView2;

    public int year, month, day;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        btc = findViewById(R.id.backtocalendar);
        plus = findViewById(R.id.add);
        //이건 예시임
        example = findViewById(R.id.example);
        textView2 = findViewById(R.id.ymd);

        //해당 년/월/일 출력
        Intent intent = getIntent();

        if (intent != null) {
            // You can check for null to make sure
            year = intent.getIntExtra("year", 2017);
            month = intent.getIntExtra("month", 1);
            day= intent.getIntExtra("day", 1);
        }
        textView2.setText(String.format("%d년 %d월 %d일의 식단 기록",year, month, day));

        //달력 버튼 누름
        btc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //+ 버튼 누름
        plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent);
            }
        });

        //디테일로 넘어가는 예시
        example.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                startActivity(intent);
            }
        });
    }
}