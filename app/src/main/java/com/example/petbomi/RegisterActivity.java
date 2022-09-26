package com.example.petbomi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private Spinner spinner;
    private EditText mEtAddress;
    private FirebaseAuth nFirebaseAuth; //파이어베이스 인증
    private DatabaseReference nDatabaseRef;
    private EditText eemail,epass,ename,enick,ephone,eage,egen,eaddr,eaddr2;
    private Button regbuttn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nFirebaseAuth=FirebaseAuth.getInstance();
        nDatabaseRef= FirebaseDatabase.getInstance().getReference();

        eemail=findViewById(R.id.emailText);
        epass=findViewById(R.id.passwordText);
        ename=findViewById(R.id.name);
        enick=findViewById(R.id.nickname);
        ephone=findViewById(R.id.phnum);
        eage=findViewById(R.id.age);
        
        eaddr=findViewById(R.id.et_address);
        eaddr2=findViewById(R.id.et_address2);


        regbuttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원가입 처리 시작
                String stremail=eemail.getText().toString();
                String strpass=epass.getText().toString();
                String strname=ename.getText().toString();
                String strnick=enick.getText().toString();
                String strphone=ephone.getText().toString();
                String strage=eage.getText().toString();
                String strgen=spinner.getSelectedItem().toString();
                String straddr=eaddr.getText().toString();
                String straddr2=eaddr2.getText().toString();

                //firebase auth 진행
                nFirebaseAuth.createUserWithEmailAndPassword(stremail,strpass).addOnCompleteListener
                        (RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser firebaseuser=nFirebaseAuth.getCurrentUser();
                                    UserAccount account=new UserAccount();
                                    account.setEmailId(firebaseuser.getEmail());
                                    account.setPassword(strpass);
                                }
                            }
                        });
            }
        });

        spinner = (Spinner)findViewById(R.id.gender);

        mEtAddress=findViewById(R.id.et_address);
        mEtAddress.setFocusable(false);
        mEtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(RegisterActivity.this,SearchActivity.class);
                getSearchResult.launch(intent);
            }
        });

    }

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
}
