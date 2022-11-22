package com.course.mydiet;

import static android.app.PendingIntent.getActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class WriteActivity extends AppCompatActivity {

    public Button back, write, addbutton;
    public TimePicker time_picker;
    public TextView time;
    public EditText foodadd, foodadd2;
    public ListView foodlist, foodlist2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        back = findViewById(R.id.back);
        write = findViewById(R.id.write);
        time_picker = findViewById(R.id.timePicker);
        foodadd = findViewById(R.id.foodadd);
        foodadd2 = findViewById(R.id.foodadd2);
        foodlist = findViewById(R.id.food_list);
        foodlist2 = findViewById(R.id.food_list2);
        addbutton = findViewById(R.id.addbutton);

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
                Intent intent = new Intent(getApplicationContext(), DietActivity.class);
                startActivity(intent);
            }
        });


        //음식 리스트
        final ArrayList<String> arrayList = new ArrayList<String>();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        final ArrayList<String> arrayList2 = new ArrayList<String>();
        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1,arrayList2);

        foodlist.setAdapter(arrayAdapter);
        foodlist2.setAdapter(arrayAdapter2);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(foodadd.getText().toString().length()>0) { // 한글자 이상 입력 된다면 추가
                    String inputStr = foodadd.getText().toString();
                    arrayList.add(inputStr);
                    arrayAdapter.notifyDataSetChanged(); //변경 이후 arrayAdapter에게 변경되었음을 알림 (새로고침)
                    foodadd.setText(null);
                }

                if(foodadd2.getText().toString().length()>0) {
                    String inputStr2 = foodadd2.getText().toString();
                    arrayList2.add(inputStr2);
                    arrayAdapter2.notifyDataSetChanged();
                    foodadd2.setText(null);
                }
            }
        });
    }

    // 키보드 내리기
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        View focusView = getCurrentFocus();
        if(focusView != null){
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int)ev.getX(), y = (int)ev.getY();
            if(!rect.contains(x, y)){
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if(imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}





