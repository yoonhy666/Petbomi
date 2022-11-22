package com.example.petbomi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookingActivity extends AppCompatActivity {

    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private final DatabaseReference mDatabaseRef = FirebaseDatabase.getInstance().getReference("petbomi");
    private final FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private final FirebaseDatabase mDatabas = FirebaseDatabase.getInstance();
    private ImageButton backbtn;
    private Button bookingbtn;
    private Intent intent;
    private String bominame, documentId, date, StartTime, FinalTime, bomiProfile, bomitel;
    private CheckBox option1, option2, option3, option4, option5, option6, option7, option8;
    private EditText text_option;
    private Booking booking;
    private CalendarView calendarView;
    private TimePicker timePicker1, timePicker2;
    private TextView tv_date, tv_time1, tv_time2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        bookingbtn = findViewById(R.id.bookingbtn);
        text_option = findViewById(R.id.text_option);
        booking = new Booking();

        //backbtn버튼 클릭
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //보미 이름, 사진, 전화번호 가져오기
        intent = getIntent();
        bominame = intent.getStringExtra("bominame");
        bomiProfile = intent.getStringExtra("bomiprofile");
        Glide.with(getApplicationContext()).load(bomiProfile);
        bomitel = intent.getStringExtra("bomitel");

        //달력 날짜 가져오기
        calendarView = findViewById(R.id.calendarView);
        tv_date = findViewById(R.id.tv_date);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                tv_date.setText((month + 1) + "월 " + day + "일");
            }
        });

        //시간 가져오기
        timePicker1 = findViewById(R.id.timePicker1);
        timePicker2 = findViewById(R.id.timePicker2);
        tv_time1 = findViewById(R.id.tv_time1);
        tv_time2 = findViewById(R.id.tv_time2);

        timePicker1.setIs24HourView(true);
        timePicker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourofDay, int minute) {
//                if (hourofDay > 12) {
//                    hourofDay -= 12;
//                    tv_time1.setText("오후 " + hourofDay + "시 " + minute + "분");
//                } else {
//                    tv_time1.setText("오전 " + hourofDay + "시 " + minute + "분");
//                }
                tv_time1.setText(String.format(Locale.getDefault(), "%02d:%02d", hourofDay, minute));
            }
        });

        timePicker2.setIs24HourView(true);
        timePicker2.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourofDay, int minute) {
//                if (hour > 12) {
//                    hour -= 12;
//                    tv_time2.setText("오후 " + hour + "시 " + minute + "분");
//                } else {
//                    tv_time2.setText("오전 " + hour + "시 " + minute + "분");
//                }
                tv_time2.setText(String.format(Locale.getDefault(), "%02d:%02d", hourofDay, minute));
            }
        });

        //Checkbox
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        option5 = findViewById(R.id.option5);
        option6 = findViewById(R.id.option6);
        option7 = findViewById(R.id.option7);
        option8 = findViewById(R.id.option8);

        String op1 = "산책";
        String op2 = "발 닦기";
        String op3 = "실내 놀이";
        String op4 = "사료 및 간식 배식";
        String op5 = "그릇 세척";
        String op6 = "모래 청소";
        String op7 = "배변패드 교체";
        String op8 = "목욕";

        //예약
        bookingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] documentId = {mAuth.getCurrentUser().getUid()};
                mDatabas.getReference().child("UserAccount").child(documentId[0])
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                                UserAccount account = datasnapshot.getValue(UserAccount.class);

                                Booking booking = new Booking();
                                booking.setDocumentId(documentId[0]);
                                booking.setText_option(text_option.getText().toString());
                                booking.setBomiId(bominame);
                                booking.setDate(tv_date.getText().toString());
                                booking.setsTime(tv_time1.getText().toString());
                                booking.setfTime(tv_time2.getText().toString());
                                booking.setBomiProfile(bomiProfile);
                                booking.setBomiTel(bomitel);

                                //체크박스 선택
                                if (option1.isChecked()) {
                                    booking.setOption1(op1);
                                } else {

                                }
                                if (option2.isChecked()) {
                                    booking.setOption2(op2);
                                } else {

                                }
                                if (option3.isChecked()) {
                                    booking.setOption3(op3);
                                } else {

                                }
                                if (option4.isChecked()) {
                                    booking.setOption4(op4);
                                } else {

                                }
                                if (option5.isChecked()) {
                                    booking.setOption5(op5);
                                } else {

                                }
                                if (option6.isChecked()) {
                                    booking.setOption6(op6);
                                } else {

                                }
                                if (option7.isChecked()) {
                                    booking.setOption7(op7);
                                } else {

                                }
                                if (option8.isChecked()) {
                                    booking.setOption8(op8);
                                } else {

                                }

                                //DB에 저장
                                mStore.collection("booking").document(mAuth.getCurrentUser().getUid()).set(booking, SetOptions.merge());
                                mDatabas.getReference().child("petbomi").child("booking").push()
                                        .setValue(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                            }
                                        });

                                startActivity();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                //사용자 documentId 가져오기
                if (mAuth.getCurrentUser() != null) {
                    mStore.collection("user").document(mAuth.getCurrentUser().getUid())
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.getResult() != null)
                                        documentId[0] = (String) task.getResult().getData().get("documentId");
                                }
                            });
                }

            }
        });

    }

    //결제페이지로 이동
    private void startActivity() {
        Intent intent = new Intent(this, PayActivity.class);
        startActivity(intent);
    }
}