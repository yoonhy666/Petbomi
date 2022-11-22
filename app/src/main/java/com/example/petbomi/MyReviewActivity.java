package com.example.petbomi;

import static java.lang.String.valueOf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MyReviewActivity extends AppCompatActivity {

    private RecyclerView mReviewRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ReviewAdapter mAdapter;
    private List<Review> mDatas;
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private FirebaseUser mAuth = FirebaseAuth.getInstance().getCurrentUser();
    private ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_review);

        mReviewRecyclerView = findViewById(R.id.myreview_recyclearview);
        layoutManager = new LinearLayoutManager(this);
        mReviewRecyclerView.setLayoutManager(layoutManager);
        mReviewRecyclerView.setHasFixedSize(true);

        //backbtn 클릭 (뒤로가기)
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    @Override
    public void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        mStore.collection("review")
                .whereEqualTo("documentId", mAuth.getUid())
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                        if (queryDocumentSnapshots != null) {
                            mDatas.clear();
                            for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()) {
                                Map<String, Object> shot = snap.getData();
                                String nickname = valueOf(shot.get("nickname"));
                                String documentId = valueOf(shot.get("documentId"));
                                float score = Float.parseFloat(shot.get("score").toString());
                                String comment = valueOf(shot.get("comment"));
                                Timestamp ts = snap.getTimestamp("timestamp");
                                Date date = ts.toDate();
                                Review data = new Review(nickname, documentId, score, comment, date);
                                mDatas.add(data);

                                mAdapter = new ReviewAdapter(mDatas);
                                mReviewRecyclerView.setAdapter(mAdapter);
                            }
                        }
                    }
                });

    }

}