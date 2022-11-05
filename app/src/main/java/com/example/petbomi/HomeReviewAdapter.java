package com.example.petbomi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeReviewAdapter extends RecyclerView.Adapter<HomeReviewAdapter.HomeReviewViewHolder> {

    private List<Review> datas;

    public HomeReviewAdapter(List<Review> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public HomeReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeReviewViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_review_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeReviewViewHolder holder, int position) {
        Review data = datas.get(position);
        holder.score.setRating(data.getScore());
        holder.comment.setText(data.getComment());
        holder.nickname.setText(data.getNickname());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class HomeReviewViewHolder extends RecyclerView.ViewHolder {

        private RatingBar score;
        private TextView comment;
        private TextView nickname;

        public HomeReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            score = itemView.findViewById(R.id.home_score);
            comment = itemView.findViewById(R.id.home_comment);
            nickname = itemView.findViewById(R.id.home_nickname);
        }
    }
}
