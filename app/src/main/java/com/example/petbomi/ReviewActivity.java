package com.example.petbomi;

import static java.lang.String.valueOf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ReviewActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mReviewRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ReviewAdapter mAdapter;
    private List<Review> mDatas;
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        mReviewRecyclerView = findViewById(R.id.review_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        mReviewRecyclerView.setLayoutManager(layoutManager);
        mReviewRecyclerView.setHasFixedSize(true);
        findViewById(R.id.writebtn).setOnClickListener(this);

    }


    @Override
    protected void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        mStore.collection("review")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                        if (queryDocumentSnapshots != null) {
                            mDatas.clear();
                            for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()) {
                                Map<String, Object> shot = snap.getData();
                                String documentId = valueOf(shot.get("documentId"));
                                float score = Float.parseFloat(shot.get("score").toString());
                                String comment = valueOf(shot.get("comment"));
                                String nickname = valueOf(shot.get("nickname"));
                                Review data = new Review(nickname, documentId, score, comment);
                                mDatas.add(data);

                                mAdapter = new ReviewAdapter(mDatas);
                                mReviewRecyclerView.setAdapter(mAdapter);
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, WriteActivity.class));
    }


}