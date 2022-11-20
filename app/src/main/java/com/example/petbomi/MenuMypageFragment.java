package com.example.petbomi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;
import java.util.List;

public class MenuMypageFragment extends Fragment {

    private RecyclerView mMypageRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MypageAdapter mAdapter;
    private List<Mypage> mDatas;
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
    private TextView mypage_review, mypage_pet_edit, mypage_edit, mypage_address, mypage_notice, logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_mypage, container, false);

        mMypageRecyclerView = rootView.findViewById(R.id.mypage_recyclearview);
        layoutManager = new LinearLayoutManager(getActivity());
        mMypageRecyclerView.setLayoutManager(layoutManager);
        mMypageRecyclerView.setHasFixedSize(true);


        //반려동물 정보 수정으로 이동
        mypage_pet_edit = rootView.findViewById(R.id.mypage_pet_edit);
        mypage_pet_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MypetEditActivity.class);
                startActivity(intent);
            }
        });

        //회원정보 수정으로 이동
        mypage_edit = rootView.findViewById(R.id.mypage_edit);
        mypage_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyEditActivity.class);
                startActivity(intent);
            }
        });

        //동네 설정하기로 이동
        mypage_address = rootView.findViewById(R.id.mypage_address);
        mypage_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyAddressActivity.class);
                startActivity(intent);
            }
        });

        //리뷰관리로 이동
        mypage_review = rootView.findViewById(R.id.mypage_review);
        mypage_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyReviewActivity.class);
                startActivity(intent);
            }
        });

        //공지사항으로 이동
        mypage_notice = rootView.findViewById(R.id.mypage_notice);
        mypage_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyNoticeActivity.class);
                startActivity(intent);
            }
        });

        //로그아웃
        logout = rootView.findViewById(R.id.mypage_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

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

