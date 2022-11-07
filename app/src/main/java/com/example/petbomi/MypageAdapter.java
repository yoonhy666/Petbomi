package com.example.petbomi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MypageAdapter extends RecyclerView.Adapter<MypageAdapter.MypageViewHolder>{

    private List<Mypage> datas;

    public MypageAdapter(List<Mypage> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public MypageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MypageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_mypage_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MypageViewHolder holder, int position) {
        Mypage data = datas.get(position);
        holder.nickname.setText(data.getNickname());
        holder.email.setText(data.getEmail());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MypageViewHolder extends RecyclerView.ViewHolder {

        private TextView nickname;
        private TextView email;

        public MypageViewHolder(@NonNull View itemView) {
            super(itemView);

            nickname = itemView.findViewById(R.id.mypage_nickname);
            email = itemView.findViewById(R.id.mypage_email);
        }
    }

}
