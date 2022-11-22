package com.example.petbomi;

import static java.lang.String.valueOf;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MenuReviewFragment extends Fragment {

    private RecyclerView mReviewRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ReviewAdapter mAdapter;
    private List<Review> mDatas;
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private ImageButton backbtn;
    private FloatingActionButton writebtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_review, container, false);

        mReviewRecyclerView = rootView.findViewById(R.id.review_recyclerview);
        layoutManager = new LinearLayoutManager(getActivity());
        mReviewRecyclerView.setLayoutManager(layoutManager);
        mReviewRecyclerView.setHasFixedSize(true);

        //writebtn
        writebtn = rootView.findViewById(R.id.writebtn);
        writebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WriteActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        mStore.collection("review")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.getResult() != null) {
                            mDatas.clear();
                            for (DocumentSnapshot snap : task.getResult().getDocuments()) {
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
