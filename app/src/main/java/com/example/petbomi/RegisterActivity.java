package com.example.petbomi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private Spinner spinner;
    private EditText mEtAddress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
