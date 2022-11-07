package com.example.petbomi;

import static java.lang.String.valueOf;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private Button find;
    private ImageButton review;
    private ImageButton go_review;
    private ImageButton mypage;
    private LinearLayoutManager layoutManager;
    private RecyclerView mHomeRecyclerView;
    private HomeReviewAdapter mAdapter;
    private List<Review> mDatas;
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private int limit = 3;

    ImageSlider imageSlider;
    ImageSlider imageSlider2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mHomeRecyclerView = findViewById(R.id.home_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mHomeRecyclerView.setLayoutManager(layoutManager);
        mHomeRecyclerView.setHasFixedSize(true);

        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startSignUpActivity();
        }

        //find버튼 클릭
        find = findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });

        //review버튼 클릭
        review = findViewById(R.id.review);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });

        //mypage버튼 클릭
        mypage = findViewById(R.id.mypage);
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });


        //go_reviewr버튼 클릭
        go_review = findViewById(R.id.go_review);
        go_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });

        //banner1
        imageSlider= findViewById(R.id.image_slider);

        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.banner1, null));
        images.add(new SlideModel(R.drawable.banner2, null));

        imageSlider.setImageList(images);


        //banner2
        imageSlider2 = findViewById(R.id.image_slider2);

        ArrayList<SlideModel> images2 = new ArrayList<>();
        images2.add(new SlideModel(R.drawable.home_banner2, null));
        images2.add(new SlideModel(R.drawable.home_banner1, null));

        imageSlider2.setImageList(images2);



    }
    private void startSignUpActivity(){
        Intent intent =new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
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
                                Review data = new Review(nickname, documentId, score, comment);
                                mDatas.add(data);

                                mAdapter = new HomeReviewAdapter(mDatas);
                                mHomeRecyclerView.setAdapter(mAdapter);
                            }
                        }
                    }
                });
    }
}