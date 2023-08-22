package com.triit.trinetv2.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.triit.trinetv2.R;
import com.triit.trinetv2.model.Data;

import java.time.LocalTime;
import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ItemVH> {
    private Context context;
    private ArrayList<Data> dataArrayList;

    public HistoryAdapter() { }
    public HistoryAdapter(Context context, ArrayList<Data> dataArrayList) {
        this.context = context;
        this.dataArrayList = dataArrayList;
    }

    public Context getContext() { return this.context; }
    public ArrayList<Data> getDataArrayList() { return this.dataArrayList; }
    public void setContext(Context context) { this.context = context; }
    public void setDataArrayList(ArrayList<Data> dataArrayList) {
        this.dataArrayList = dataArrayList;
    }

    @NonNull
    @Override
    public ItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.history_list, parent, false);

        return new ItemVH(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemVH holder, int position) {
        LocalTime localTime = dataArrayList.get(position).getTime();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.tvTime.setText(String.format("%d:%d:%d",
                    localTime.getHour(), localTime.getMinute(), localTime.getSecond()));
        } else {
            holder.tvTime.setText("null");
        }
        holder.tvValue.setText(String.format("%s", dataArrayList.get(position).getData()));
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
