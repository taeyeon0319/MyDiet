package com.course.mydiet;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class inputActivity extends AppCompatActivity {

    public EditText input,input1,input2,input3;
    public Button fin;
    public TextView text,text1,text2,text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);


        fin = (Button)findViewById(R.id.finish);


        //완료버튼 ==> 저장 완료후 뒤로 돌아감 but 아직 저장 구현x
        fin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent);
            }
        });


        input =(EditText)findViewById(R.id.input);      // 수량 입력받음
        input1 =(EditText)findViewById(R.id.input1);    //음식이름 입력받음
        input2 =(EditText)findViewById(R.id.input2);    //소감 입력받음
        input3 =(EditText)findViewById(R.id.time2);      // 시간 입력받음


            //TextView


        text = (TextView)findViewById(R.id.food);   //음식이름:
        text1 = (TextView)findViewById(R.id.count);  //수량:
        text2 = (TextView)findViewById(R.id.review); //소감:
        text3 = (TextView)findViewById(R.id.time1);  //시간:




    }
}


