package com.example.petbomi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookingfinalActivity extends FragmentActivity {

    private Button btn;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction transaction = fragmentManager.beginTransaction();
    private MenuBookingFragment menuBookingFragment = new MenuBookingFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_fin);

            btn = findViewById(R.id.go_history);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    transaction.replace(R.id.menu_frame_layout, menuBookingFragment).commitAllowingStateLoss();
                }
            });

        }
}