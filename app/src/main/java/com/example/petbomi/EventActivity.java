package com.example.petbomi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

public class EventActivity extends AppCompatActivity {

    private EventAdapter mAdapter;
    private ListView eventView;
    private ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        //backbtn
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAdapter = new EventAdapter();
        eventView = (ListView) findViewById(R.id.eventView);

        setData();

        eventView.setAdapter(mAdapter);

    }

    private void setData() {

        TypedArray arrbanner = getResources().obtainTypedArray(R.array.banner);

        for (int i = 0; i < arrbanner.length(); i++) {

            Event event = new Event();
            event.setBanner(arrbanner.getResourceId(i, 0));

            mAdapter.addItem(event);
        }
    }

}