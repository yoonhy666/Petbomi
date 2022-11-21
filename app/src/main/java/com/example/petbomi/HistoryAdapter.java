package com.example.petbomi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {


    private List<Booking> datas;
    private Context context;
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
        holder.bomiTel.setText(data.getBomiTel());
        Glide.with(holder.itemView)
                .load(datas.get(position).getBomiProfile())
                .into(holder.bomiProfile);

        if (data.getOption1() != "null") {
            holder.option1.setVisibility(View.VISIBLE);
            holder.option1.setText(data.getOption1());
        } else {
            holder.option1.setVisibility(View.GONE);
            holder.line1.setVisibility(View.GONE);
        }

        if (data.getOption2() != "null") {
            holder.option2.setVisibility(View.VISIBLE);
            holder.option2.setText(data.getOption2());
        } else {
            holder.option2.setVisibility(View.GONE);
            holder.line2.setVisibility(View.GONE);
        }

        if (data.getOption3() != "null") {
            holder.option3.setVisibility(View.VISIBLE);
            holder.option3.setText(data.getOption3());
        } else {
            holder.option3.setVisibility(View.GONE);
            holder.line3.setVisibility(View.GONE);
        }

        if (data.getOption4() != "null") {
            holder.option4.setVisibility(View.VISIBLE);
            holder.option4.setText(data.getOption4());
        } else {
            holder.option4.setVisibility(View.GONE);
            holder.line4.setVisibility(View.GONE);
        }

        if (data.getOption5() != "null") {
            holder.option5.setVisibility(View.VISIBLE);
            holder.option5.setText(data.getOption5());
        } else {
            holder.option5.setVisibility(View.GONE);
            holder.line5.setVisibility(View.GONE);
        }

        if (data.getOption6() != "null") {
            holder.option6.setVisibility(View.VISIBLE);
            holder.option6.setText(data.getOption6());
        } else {
            holder.option6.setVisibility(View.GONE);
            holder.line6.setVisibility(View.GONE);
        }

        if (data.getOption7() != "null") {
            holder.option7.setVisibility(View.VISIBLE);
            holder.option7.setText(data.getOption7());
        } else {
            holder.option7.setVisibility(View.GONE);
            holder.line7.setVisibility(View.GONE);
        }

        if (data.getOption8() != "null") {
            holder.option8.setVisibility(View.VISIBLE);
            holder.option8.setText(data.getOption8());
        } else {
            holder.option8.setVisibility(View.GONE);
            holder.line8.setVisibility(View.GONE);
        }

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
        private ImageView line1;
        private ImageView line2;
        private ImageView line3;
        private ImageView line4;
        private ImageView line5;
        private ImageView line6;
        private ImageView line7;
        private ImageView line8;
        private Button history_call;
        private Button history_sms;
        private TextView bomiTel;


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
            line1 = itemView.findViewById(R.id.h_line1);
            line2 = itemView.findViewById(R.id.line2);
            line3 = itemView.findViewById(R.id.line3);
            line4 = itemView.findViewById(R.id.line4);
            line5 = itemView.findViewById(R.id.line5);
            line6 = itemView.findViewById(R.id.line6);
            line7 = itemView.findViewById(R.id.line7);
            line8 = itemView.findViewById(R.id.line8);
            bomiTel = itemView.findViewById(R.id.history_bomitel);

            history_call = itemView.findViewById(R.id.history_call);
            history_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("tel:"+bomiTel.getText());
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    v.getContext().startActivity(intent);
                }
            });

            history_sms = itemView.findViewById(R.id.history_sms);
            history_sms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("sms:"+bomiTel.getText());
                    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                    intent.putExtra("sms_body", "보미님 안녕하세요");
                    v.getContext().startActivity(intent);

                }
            });




        }
    }
}
