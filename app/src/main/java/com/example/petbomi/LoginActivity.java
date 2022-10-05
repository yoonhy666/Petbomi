package com.example.petbomi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.petbomi.Util.showToast;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth=FirebaseAuth.getInstance();

        TextView text=(TextView) findViewById(R.id.registerButton);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        ImageButton imagebutton= (ImageButton) findViewById(R.id.backbtn);

        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


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
    private void SignUp() {
        String email = ((EditText) findViewById(R.id.emailText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordText)).getText().toString();


        if(email.length() > 0 && password.length() > 0){
            if (password.equals(passwordCheck)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startToast("회원가입에 성공하셨습니다.");
                                    //ui
                                } else {
                                    if(task.getException()!=null){
                                        startToast(task.getException().toString());
                                    }

                                }
                            }
                        });
            } else {
                startToast("비밀번호가 일치하지 않습니다.");
            }
        }else{
            startToast("이메일 또는 비밀번호를 입력해 주세요.");
        }

    }
    private void startToast(String msg){


        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();


    }
}
