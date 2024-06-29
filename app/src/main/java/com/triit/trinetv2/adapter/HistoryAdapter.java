package com.triit.trinetv2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triit.trinetv2.R;
import com.triit.trinetv2.models.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ItemVH> {
    private Context context;
    private ArrayList<Data> dataArrayList;

    public HistoryAdapter() { }
    public HistoryAdapter(Context context, ArrayList<Data> dataArrayList) {
        this.context = context;
        this.dataArrayList = dataArrayList;
    }

    @NonNull
    @Override
    public ItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.history_list, parent, false);

        return new ItemVH(view);
    }
    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ItemVH holder, int position) {
        LocalTime localTime = dataArrayList.get(position).getTime();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.tvTime.setText(String.format(
                    new Locale("vi-VN"), "%2d:%2d:%2d",
                    localTime.getHour(), localTime.getMinute(), localTime.getSecond()));
        } else {
            holder.tvTime.setText("N/A");
        }

        holder.tvValue.setText(String.format("%.2f", dataArrayList.get(position).getData()));
    }
    @Override
    public int getItemCount() { return this.dataArrayList.size(); }

    public void clearHistory() {
        dataArrayList.clear();
        notifyDataSetChanged();
    }

    public static class ItemVH extends RecyclerView.ViewHolder {
        TextView tvTime, tvValue;

        public ItemVH(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvValue = itemView.findViewById(R.id.tvValue);
        }
    }
}
