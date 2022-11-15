package com.example.petbomi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class BookingActivity extends AppCompatActivity {

    private ImageButton backbtn;
    private Button find2;
    private TextView bomi_name;
    private Intent intent;
    String name;

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


    }
}