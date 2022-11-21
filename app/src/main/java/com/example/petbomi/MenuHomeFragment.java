package com.example.petbomi;

import static java.lang.String.valueOf;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
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

public class MenuHomeFragment extends Fragment {

    private Button find;
    private ImageButton go_review, go_event;
    private LinearLayoutManager layoutManager;
    private RecyclerView mHomeRecyclerView;
    private HomeReviewAdapter mAdapter;
    private List<Review> mDatas;
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private int limit = 3;
    ImageSlider imageSlider;
    ImageSlider imageSlider2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_home, container, false);

        mHomeRecyclerView = rootView.findViewById(R.id.home_recyclerview);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mHomeRecyclerView.setLayoutManager(layoutManager);
        mHomeRecyclerView.setHasFixedSize(true);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startSignUpActivity();
        }

        //find버튼 클릭 (fragment에서 activity로 화면 전환)
        find = rootView.findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LocationActivity.class);
                startActivity(intent);
            }
        });

        //go_review버튼 클릭 (fragment간 화면 전환)
        go_review = rootView.findViewById(R.id.go_review);
        go_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                MenuReviewFragment reviewFragment = new MenuReviewFragment();
                transaction.replace(R.id.menu_frame_layout, reviewFragment);
                transaction.commit();
            }
        });

        //go_event버튼 클릭
        go_event = rootView.findViewById(R.id.go_event);
        go_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EventActivity.class);
                startActivity(intent);
            }
        });

        //banner1
        imageSlider = rootView.findViewById(R.id.image_slider);

        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.banner1, null));
        images.add(new SlideModel(R.drawable.banner2, null));

        imageSlider.setImageList(images);


        //banner2
        imageSlider2 = rootView.findViewById(R.id.image_slider2);

        ArrayList<SlideModel> images2 = new ArrayList<>();
        images2.add(new SlideModel(R.drawable.home_banner2, null));
        images2.add(new SlideModel(R.drawable.home_banner1, null));
        images2.add(new SlideModel(R.drawable.home_banner3, null));

        imageSlider2.setImageList(images2);

        return rootView;

    }

    private void startSignUpActivity() {
        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        mStore.collection("review")
                .orderBy("timestamp", Query.Direction.DESCENDING).limit(limit)
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

                                mAdapter = new HomeReviewAdapter(mDatas);
                                mHomeRecyclerView.setAdapter(mAdapter);
                            }
                        }
                    }
                });
    }
}