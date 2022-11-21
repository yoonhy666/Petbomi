package com.example.petbomi;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CustomViewHolder> {

    private ArrayList<User> arrayList;
    private Context context;

    public UserAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_sheet_item, parent, false);
        CustomViewHolder holer = new CustomViewHolder(view);


        return holer;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.item_profile);

        holder.item_name.setText(arrayList.get(position).getName());
        holder.item_addr.setText(arrayList.get(position).getAddr());
        holder.item_score.setRating(arrayList.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView item_profile;
        TextView item_name;
        TextView item_addr;
        RatingBar item_score;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.item_profile = itemView.findViewById(R.id.item_profile);
            this.item_name = itemView.findViewById(R.id.item_name);
            this.item_addr = itemView.findViewById(R.id.item_addr);
            this.item_score = itemView.findViewById(R.id.item_score);

            //클릭 이벤트
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentPos = getBindingAdapterPosition(); //click position
                    User user = arrayList.get(currentPos);
                    Intent intent;
                    intent = new Intent(context, BookingActivity.class);
                    intent.putExtra("bominame", user.getName());
                    intent.putExtra("bomiprofile", arrayList.get(currentPos).getProfile());
                    intent.putExtra("bomitel", user.getTel());
                    context.startActivity(intent);
                }
            });

        }
    }
}