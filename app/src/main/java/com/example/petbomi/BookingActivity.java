package com.example.petbomi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class BookingActivity extends AppCompatActivity {

    private ImageButton backbtn;
    private Button find2;
    private TextView bomi_name;
    private Intent intent;
    String name;

    //달력
    CalendarView calendar;
    TextView tv_date;

    //시간
    TimePicker time_picker;
    TextView tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        //backbtn버튼 클릭
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        //find2버튼 클릭
        find2 = findViewById(R.id.find2);
        find2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingActivity.this, PayActivity.class);
                startActivity(intent);
            }
        });

        //보미 이름 가져오기
        intent = getIntent();
        name = intent.getStringExtra("name");

        bomi_name = findViewById(R.id.bomi_name);
        bomi_name.setText(name);

        //달력 날짜 가져오기
        calendar = findViewById(R.id.calendar);
        tv_date = findViewById(R.id.tv_date);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                tv_date.setText(year + "년 " + (month + 1) + "월 " + day + "일 선택");
            }

        });

        //시간 가져오기
        time_picker = findViewById(R.id.time_picker);
        tv_time = findViewById(R.id.tv_time);

        time_picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                if (hour > 12) {
                    hour -= 12;
                    tv_time.setText("오후 " + hour + "시 " + minute + "분 선택");
                } else {
                    tv_time.setText("오전 " + hour + "시 " + minute + "분 선택");
                }
            }
        });

    }
}