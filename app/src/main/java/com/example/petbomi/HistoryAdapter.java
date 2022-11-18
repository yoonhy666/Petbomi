package com.example.petbomi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {


    private List<Booking> datas;
    public HistoryAdapter(List<Booking> datas) { this.datas = datas; }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_booking_history_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        Booking data = datas.get(position);
        holder.date.setText(data.getDate());
        holder.sTime.setText(data.getsTime());
        holder.fTime.setText(data.getfTime());
        holder.bomiId.setText(data.getBomiId());
        holder.option1.setText(data.getOption1());
        holder.option2.setText(data.getOption2());
        holder.option3.setText(data.getOption3());
        holder.option4.setText(data.getOption4());
        holder.option5.setText(data.getOption5());
        holder.option6.setText(data.getOption6());
        holder.option7.setText(data.getOption7());
        holder.option8.setText(data.getOption8());

        Glide.with(holder.itemView)
                .load(datas.get(position).getBomiProfile())
                .into(holder.bomiProfile);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView date;
        private TextView sTime;
        private TextView fTime;
        private TextView bomiId;
        private TextView option1;
        private TextView option2;
        private TextView option3;
        private TextView option4;
        private TextView option5;
        private TextView option6;
        private TextView option7;
        private TextView option8;
        private CircleImageView bomiProfile;


        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.history_date);
            sTime = itemView.findViewById(R.id.history_stime);
            fTime = itemView.findViewById(R.id.history_ftime);
            bomiId = itemView.findViewById(R.id.history_bominame);
            option1 = itemView.findViewById(R.id.history_option1);
            option2 = itemView.findViewById(R.id.history_option2);
            option3 = itemView.findViewById(R.id.history_option3);
            option4 = itemView.findViewById(R.id.history_option4);
            option5 = itemView.findViewById(R.id.history_option5);
            option6 = itemView.findViewById(R.id.history_option6);
            option7 = itemView.findViewById(R.id.history_option7);
            option8 = itemView.findViewById(R.id.history_option8);
            bomiProfile = itemView.findViewById(R.id.history_profile);


        }
    }
}
