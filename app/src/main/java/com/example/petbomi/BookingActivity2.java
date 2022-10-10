package com.example.petbomi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class BookingActivity2 extends AppCompatActivity {

    private TextView cat_choice;
    private ImageButton backbtn;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking2);

        //cat클릭
        cat_choice = findViewById(R.id.cat_choice);
        cat_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingActivity2.this, BookingActivity3.class);
                startActivity(intent);
            }
        });

        //backbtn버튼 클릭
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingActivity2.this, BookingActivity1.class);
                startActivity(intent);
            }
        });

        //next버튼 클릭
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingActivity2.this, PayActivity.class);
                startActivity(intent);
            }
        });
    }
}