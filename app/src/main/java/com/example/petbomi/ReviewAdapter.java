package com.example.petbomi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<Review> datas;

    public ReviewAdapter(List<Review> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_review_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review data = datas.get(position);
        holder.score.setRating(data.getScore());
        holder.comment.setText(data.getComment());
        holder.nickname.setText(data.getNickname());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {

        private RatingBar score;
        private TextView comment;
        private TextView nickname;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            score = itemView.findViewById(R.id.review_score);
            comment = itemView.findViewById(R.id.review_comment);
            nickname = itemView.findViewById(R.id.review_nickname);
        }
    }

}
