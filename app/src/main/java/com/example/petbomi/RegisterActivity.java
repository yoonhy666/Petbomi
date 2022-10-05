package com.example.petbomi;

<<<<<<< HEAD
import android.annotation.SuppressLint;
import android.content.Context;
=======
import android.app.DatePickerDialog;
>>>>>>> 94f72d1d960effa481a53827ebcfbeccc78b5754
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.ImageButton;
import android.widget.Spinner;
import android.app.DatePickerDialog;
import android.widget.Toast;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;
=======
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
>>>>>>> 94f72d1d960effa481a53827ebcfbeccc78b5754


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity {

<<<<<<< HEAD

    private EditText mEtAddress;
    private FirebaseAuth nFirebaseAuth; //파이어베이스 인증
    private DatabaseReference nDatabaseRef;
=======
    private static final String TAG="RegisterActivity";
    private EditText mEtAddress;
    private FirebaseAuth mAuth;

    Calendar calendar=Calendar.getInstance();
    DatePickerDialog.OnDateSetListener datepicker=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,day);
            updateLabel();
        }
    };


>>>>>>> 94f72d1d960effa481a53827ebcfbeccc78b5754

    private Button regbtn;

    Calendar calendar=Calendar.getInstance();



    DatePickerDialog.OnDateSetListener datepicker=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,day);
            updateLabel();
        }
    };



    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

<<<<<<< HEAD
=======


>>>>>>> 94f72d1d960effa481a53827ebcfbeccc78b5754
        EditText et_Date = (EditText) findViewById(R.id.birth);
        et_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this, datepicker, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



<<<<<<< HEAD



        ImageButton backbutton= (ImageButton) findViewById(R.id.backbtn);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
=======
>>>>>>> 94f72d1d960effa481a53827ebcfbeccc78b5754

        mEtAddress=findViewById(R.id.et_address);
        mEtAddress.setFocusable(false);
        mEtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

<<<<<<< HEAD

    }
=======
        findViewById(R.id.signUpButton).setOnClickListener(onClickListener);
        mAuth=FirebaseAuth.getInstance();
    }


>>>>>>> 94f72d1d960effa481a53827ebcfbeccc78b5754
    private final ActivityResultLauncher<Intent> getSearchResult=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode()==RESULT_OK){

                    if(result.getData()!=null){
                        String data=result.getData().getStringExtra("data");
                        mEtAddress.setText(data);
                    }
                }
            }
    );
<<<<<<< HEAD



=======
>>>>>>> 94f72d1d960effa481a53827ebcfbeccc78b5754
    private void updateLabel() {
        String myFormat = "yyyy/MM/dd";    // 출력형식   2021/07/26
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        EditText et_date = (EditText) findViewById(R.id.birth);
        et_date.setText(sdf.format(calendar.getTime()));
<<<<<<< HEAD

=======
    }
    public void toStart(){
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
    }

    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.signUpButton:
                    SignUp();

                    break;
            }

        }
    };
    private void SignUp(){
        String email=((EditText)findViewById(R.id.emailText)).getText().toString();
        String password=((EditText)findViewById(R.id.passwordText)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.passwordText_re)).getText().toString();
        String name=((EditText)findViewById(R.id.name)).getText().toString();
        String phone=((EditText)findViewById(R.id.phone)).getText().toString();
        String nickname=((EditText)findViewById(R.id.nickname)).getText().toString();
        String birth=((EditText)findViewById(R.id.birth)).getText().toString();
        String petname=((EditText)findViewById(R.id.petname)).getText().toString();
        RadioGroup petkind=((RadioGroup)findViewById(R.id.petkind));
        RadioGroup petgender=((RadioGroup)findViewById(R.id.petgender));
        RadioButton kindbtn = findViewById( petkind.getCheckedRadioButtonId() );
        RadioButton genbtn = findViewById( petgender.getCheckedRadioButtonId() );
        String kind=kindbtn.getText().toString().toUpperCase();
        String gender=genbtn.getText().toString().toUpperCase();
        String petage=((EditText)findViewById(R.id.petage)).getText().toString();
        String address=((EditText)findViewById(R.id.et_address)).getText().toString();
        String address2=((EditText)findViewById(R.id.et_address2)).getText().toString();
        String etc=((EditText)findViewById(R.id.etc)).getText().toString();



        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //ui
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                           //ui


                        }
                    }
                });
>>>>>>> 94f72d1d960effa481a53827ebcfbeccc78b5754
    }
}
