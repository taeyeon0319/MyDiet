package com.course.mydiet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public CalendarView calendarView;
    public TextView yymmdd,calendartitle, calorie;
    public Button detail_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView=findViewById(R.id.calendarView);
        yymmdd=findViewById(R.id.yymmdd);
        calendartitle=findViewById(R.id.calendartitle);
        detail_btn = findViewById(R.id.detailbutton);
        calorie = findViewById(R.id.calorie);

        //날짜 선택
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                detail_btn.setVisibility(View.VISIBLE);
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
                yymmdd.setVisibility(View.VISIBLE);
                yymmdd.setText(String.format("%d / %d / %d",year,month+1,dayOfMonth));
            }
        });
    }
}