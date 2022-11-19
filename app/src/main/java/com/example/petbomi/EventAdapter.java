package com.example.petbomi;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class EventAdapter extends BaseAdapter {

    private ArrayList<Event> datas = new ArrayList<>();

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_event_detail, null, false);
            holder = new CustomViewHolder();
            holder.banner = (ImageView) convertView.findViewById(R.id.event_banner);

            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        Event event = datas.get(position);

        holder.banner.setImageResource(event.getBanner());

        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        return convertView;
    }

    class CustomViewHolder {

        ImageView banner;

    }

    public void addItem(Event event) {
        datas.add(event);
    }


}
