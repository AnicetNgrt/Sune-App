package com.example.suneapp.adapter;

import android.annotation.SuppressLint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suneapp.R;
import com.example.suneapp.model.LogApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.List;

public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.ViewHolder> {
    private final List<LogApplication> data;
    private final OnLogListener mOnLogListener;

    public interface OnLogListener {
        void onLogClicked(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView logId;
        private final TextView logHour;
        private final OnLogListener onLogListener;

        public ViewHolder(@NonNull View itemView, OnLogListener onLogListener) {
            super(itemView);
            this.onLogListener = onLogListener;
            // reference text view on log_item.xml
            this.logId = itemView.findViewById(R.id.log_id_textView);
            this.logHour = itemView.findViewById(R.id.log_hour_textView);
            itemView.setOnClickListener(this);
        }

        public TextView getLogId() {
            return logId;
        }

        public TextView getLogHour() {
            return logHour;
        }

        @Override
        public void onClick(View v) {
            onLogListener.onLogClicked(getAdapterPosition());
        }
    }

    public LogsAdapter(List<LogApplication> data, OnLogListener mOnLogListener) {
        this.data = data;
        this.mOnLogListener = mOnLogListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.log_item, parent, false);

        return new ViewHolder(view, mOnLogListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LogApplication logApplication = data.get(position);
        holder.getLogId().setText(logApplication.getId());

        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("HH:mm");
        String date = df.format(logApplication.getDate());
        holder.getLogHour().setText(date);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
