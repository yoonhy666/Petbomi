package com.example.petbomi;

import static java.lang.String.valueOf;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuBookingFragment extends Fragment {

    private RecyclerView mHistoryRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HistoryAdapter mAdapter;
    private List<Booking> mDatas;
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private FirebaseUser mAuth = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_booking_history, container, false);


        mHistoryRecyclerView = rootView.findViewById(R.id.history_recyclearview);
        layoutManager = new LinearLayoutManager(getActivity());
        mHistoryRecyclerView.setLayoutManager(layoutManager);
        mHistoryRecyclerView.setHasFixedSize(true);

        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        mStore.collection("booking")
                .whereEqualTo("documentId", mAuth.getUid())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                        if (queryDocumentSnapshots != null) {
                            mDatas.clear();
                            for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()) {
                                Map<String, Object> shot = snap.getData();
                                String date = valueOf(shot.get("date"));
                                String sTime = valueOf(shot.get("sTime"));
                                String fTime = valueOf(shot.get("fTime"));
                                String bomiId = valueOf(shot.get("bomiId"));
                                String option1 = valueOf(shot.get("option1"));
                                String option2 = valueOf(shot.get("option2"));
                                String option3 = valueOf(shot.get("option3"));
                                String option4 = valueOf(shot.get("option4"));
                                String option5 = valueOf(shot.get("option5"));
                                String option6 = valueOf(shot.get("option6"));
                                String option7 = valueOf(shot.get("option7"));
                                String option8 = valueOf(shot.get("option8"));
                                String text_option = valueOf(shot.get("text_option"));
                                String documentId = valueOf(shot.get("documentId"));
                                String bomiProfile = valueOf(shot.get("bomiProfile"));
                                String bomiTel = valueOf(shot.get("bomiTel"));

                                Booking data = new Booking(option1, option2, option3, option4, option5, option6, option7, option8,
                                        text_option, documentId, bomiId, date, sTime, fTime, bomiProfile, bomiTel);
                                mDatas.add(data);

                                mAdapter = new HistoryAdapter(mDatas);
                                mHistoryRecyclerView.setAdapter(mAdapter);
                            }
                        }
                    }
                });
    }
}
