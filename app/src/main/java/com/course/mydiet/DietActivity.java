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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.course.mydiet.db.*;

import java.util.List;

public class DietActivity extends AppCompatActivity{

    public Button btc;
    //public Button plus;
    public TextView textView2;

    private List<Diet> dietList;
    private DietDB dietDB = null;
    private Context mContext = null;
    private DietAdapter dietAdapter;
    private RecyclerView mRecyclerView;


    public int year, month, day;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        btc = findViewById(R.id.backtocalendar);
        //plus = findViewById(R.id.add);
        textView2 = findViewById(R.id.ymd);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        mContext = getApplicationContext();
        dietAdapter = new DietAdapter(dietList);

        //해당 년/월/일 출력
        Intent intent = getIntent();

        if (intent != null) {
            // You can check for null to make sure
            year = intent.getIntExtra("year", 2017);
            month = intent.getIntExtra("month", 1);
            day= intent.getIntExtra("day", 1);
        }
        textView2.setText(String.format("%d년 %d월 %d일의 식단 기록",year, month, day));

        // thread를 이용해 data읽기
        class InsertRunnable implements Runnable {
            @Override
            public void run() {
                try {
                    dietList = DietDB.getInstance(mContext).dietDao().loadAllByDate(String.format("%d.%d.%d.",year, month, day));
                    dietAdapter = new DietAdapter(dietList);
                    dietAdapter.notifyDataSetChanged();

                    mRecyclerView.setAdapter(dietAdapter);
                    LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
                    mRecyclerView.setLayoutManager(mLinearLayoutManager);
                }
                catch (Exception e) {

                }
            }
        }
        InsertRunnable insertRunnable = new InsertRunnable();
        Thread t = new Thread(insertRunnable);
        t.start();


        //달력 버튼 누름
        btc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //+ 버튼 누름
        /*plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent);
            }
        });*/
    }
}