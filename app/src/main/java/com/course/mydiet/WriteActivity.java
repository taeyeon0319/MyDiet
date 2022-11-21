package com.course.mydiet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WriteActivity extends AppCompatActivity {

    public Button back;
    public Button write;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        back = findViewById(R.id.back);
        write = findViewById(R.id.write);

        //뒤로 버튼 ==> 추가 없이
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), DietActivity.class);
                startActivity(intent);
            }
        });

        //작성 버튼 ==> 새로운 내용 추가
        write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), inputActivity.class);
                startActivity(intent);
            }
        });
    }
}





