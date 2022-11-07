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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MypageActivity extends AppCompatActivity {

    private RecyclerView mMypageRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MypageAdapter mAdapter;
    private List<Mypage> mDatas;
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
    private ImageButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        mMypageRecyclerView = findViewById(R.id.mypage_recyclearview);
        layoutManager = new LinearLayoutManager(this);
        mMypageRecyclerView.setLayoutManager(layoutManager);
        mMypageRecyclerView.setHasFixedSize(true);

        //homebtn
        home = findViewById(R.id.home_x);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MypageActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        mDatas = new ArrayList<>();
//        mStore.collection("user")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
//                        if (queryDocumentSnapshots != null) {
//                            mDatas.clear();
//                            for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()) {
//                                Map<String, Object> shot = snap.getData();
//                                String nickname = valueOf(shot.get("nickname"));
//                                String email = valueOf(shot.get("email"));
//                                Mypage data = new Mypage(nickname, email);
//                                mDatas.add(data);
//
//                                mAdapter = new MypageAdapter(mDatas);
//                                mMypageRecyclerView.setAdapter(mAdapter);
//                            }
//                        }
//                    }
//                });

        if (mUser != null) {
            mDatas = new ArrayList<>();
            mStore.collection("user").document(mUser.getUid())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult() != null) {
                                    String nickname = String.valueOf(task.getResult().get("nickname"));
                                    String email = String.valueOf(task.getResult().get("email"));
                                    Mypage data = new Mypage(nickname, email);
                                    mDatas.add(data);

                                    mAdapter = new MypageAdapter(mDatas);
                                    mMypageRecyclerView.setAdapter(mAdapter);
                                }
                            }
                        }
                    });
        }
    }
}