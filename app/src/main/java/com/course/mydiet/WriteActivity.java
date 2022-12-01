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

import com.course.mydiet.dietdb.Diet;
import com.course.mydiet.dietdb.DietDB;
import com.course.mydiet.fooddb.Food;
import com.course.mydiet.fooddb.FoodDB;

import java.util.ArrayList;

public class WriteActivity extends AppCompatActivity {
    private DietDB dietDB = null;
    private FoodDB foodDB = null;
    private Context dContext;

    public Button back, write, addbutton;
    public TimePicker time_picker;
    public TextView time;
    public EditText diettitle, foodadd, foodadd2, dietreview;
    public ListView foodlist, foodlist2;

    public int year, month, day;
    public String timeread;

    public String f, n;
    public String t;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        back = findViewById(R.id.back);
        write = findViewById(R.id.write);
        time_picker = findViewById(R.id.foodimage);
        diettitle = findViewById(R.id.diet_title);
        foodadd = findViewById(R.id.foodadd);
        foodadd2 = findViewById(R.id.foodadd2);
        foodlist = findViewById(R.id.food_list);
        foodlist2 = findViewById(R.id.food_list2);
        addbutton = findViewById(R.id.addbutton);
        dietreview = findViewById(R.id.dietreview);

        //DB 생성
        dietDB = DietDB.getInstance(this);
        foodDB = FoodDB.getInstance(this);
        dContext = getApplicationContext();

        //날짜 값 전달 받음
        Intent intent = getIntent();
        if (intent != null) {
            // You can check for null to make sure
            year = intent.getIntExtra("year", 2017);
            month = intent.getIntExtra("month", 1);
            day= intent.getIntExtra("day", 1);
        }

        // TimePicker 클릭 시 출력
        time_picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                if(hour>12){
                    hour-=12;
                    timeread = String.format("오후 %d시 %d분",hour, minute);
                }else{
                    timeread = String.format("오전 %d시 %d분",hour, minute);
                }
            }
        });

        // DB INSERT
        class InsertRunnable implements Runnable{
            @Override
            public void run(){
                Diet diet = new Diet();
                diet.date = String.format("%d.%d.%d.",year, month, day);
                diet.title = diettitle.getText().toString();
                diet.time = timeread;
                diet.review = dietreview.getText().toString();
                DietDB.getInstance(dContext).dietDao().insertAll(diet);
            }
        }

        //뒤로 버튼 ==> 추가 없이
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        //작성 버튼 ==> 새로운 내용 추가
        write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                InsertRunnable insertRunnable = new InsertRunnable();
                Thread addThread = new Thread(insertRunnable);
                addThread.start();

                Intent intent = new Intent(getApplicationContext(), DietActivity.class);
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                startActivity(intent);
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
                //음식 이름
                if(foodadd.getText().toString().length()>0) { // 한글자 이상 입력 된다면 추가
                    String inputStr = foodadd.getText().toString();
                    f = foodadd.getText().toString();
                    arrayList.add(inputStr);
                    arrayAdapter.notifyDataSetChanged(); //변경 이후 arrayAdapter에게 변경되었음을 알림 (새로고침)
                    foodadd.setText(null);
                }
                //음식 수량
                if(foodadd2.getText().toString().length()>0) {
                    String inputStr2 = foodadd2.getText().toString();
                    n = foodadd2.getText().toString();
                    arrayList2.add(inputStr2);
                    arrayAdapter2.notifyDataSetChanged();
                    foodadd2.setText(null);
                }

                class InsertFoodRunnable implements Runnable{
                    @Override
                    public void run(){
                        Food food = new Food();
                        food.foodname = f;
                        try{
                            food.amount = Integer.parseInt(n);
                        } catch (NumberFormatException e){
                        } catch (Exception e){
                        }

                        food.connectdiet = String.format("%d.%d.%d.-%s",year, month, day, diettitle.getText().toString());
                        FoodDB.getInstance(dContext).foodDao().insertAll(food);
                    }
                }
                InsertFoodRunnable insertfoodRunnable = new InsertFoodRunnable();
                Thread add2Thread = new Thread(insertfoodRunnable);
                add2Thread.start();
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





