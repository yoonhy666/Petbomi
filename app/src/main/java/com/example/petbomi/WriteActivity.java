package com.example.petbomi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class WriteActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private RatingBar mScore;
    private EditText mComment;
    private EditText mNickname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        mScore = findViewById(R.id.write_score);
        mComment = findViewById(R.id.write_comment);
        mNickname = findViewById(R.id.write_nickname);

        mScore.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            }
        });

        findViewById(R.id.savebtn).setOnClickListener(this);

//        if (mAuth.getCurrentUser() != null) {
//            mStore.collection("nickname").document(mAuth.getCurrentUser().getUid())
//                   .get()
//                   .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                            if (task.getResult() != null) {
//                                nickname = (String) task.getResult().getData().get("nickname");
//                            }
//                        }
//                    });
//        }
    }


    @Override
    public void onClick(View v) {
        if (mAuth.getCurrentUser() != null) {
            String reviewId = mStore.collection("review").document().getId();
            Map<String, Object> data = new HashMap<>();
            data.put("documentId", mAuth.getCurrentUser().getUid());
            data.put("score", mScore.getRating());
            data.put("comment", mComment.getText().toString());
            data.put("nickname", mNickname.getText().toString());
            data.put("timestamp", FieldValue.serverTimestamp());
            mStore.collection("review").document(reviewId).set(data, SetOptions.merge());
            finish();
        }
    }
}