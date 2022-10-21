package com.example.petbomi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Button find;
    private ImageButton review;
    private ImageButton go_calender;
    private ImageButton go_review;
    ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startSignUpActivity();
        }

        //find버튼 클릭
        find = findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BookingActivity.class);
                startActivity(intent);
            }
        });

        //review버튼 클릭
        review = findViewById(R.id.review);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });

        //go_calender버튼 클릭
        go_calender = findViewById(R.id.go_calender);
        go_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });

        //go_reviewr버튼 클릭
        go_review = findViewById(R.id.go_review);
        go_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });

        //banner
        imageSlider = findViewById(R.id.image_slider);

        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.banner1, null));
        images.add(new SlideModel(R.drawable.banner2, null));

        imageSlider.setImageList(images);
    }
    private void startSignUpActivity(){
        Intent intent =new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

}