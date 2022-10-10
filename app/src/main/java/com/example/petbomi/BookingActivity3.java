package com.example.petbomi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class BookingActivity3 extends AppCompatActivity {

    private TextView dog_choice;
    private ImageButton backbtn;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking3);

        //dog클릭
        dog_choice = findViewById(R.id.dog_choice);
        dog_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingActivity3.this, BookingActivity2.class);
                startActivity(intent);
            }
        });

        //backbtn버튼 클릭
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingActivity3.this, BookingActivity1.class);
                startActivity(intent);
            }
        });

        //next버튼 클릭
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingActivity3.this, PayActivity.class);
                startActivity(intent);
            }
        });
    }
}