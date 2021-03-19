package com.example.suneapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.suneapp.R;
import com.example.suneapp.model.LogApplicationDocument;

import java.util.List;

public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.ViewHolder> {
    private final OnLogListener mOnLogListener;
    private List<LogApplicationDocument> data;

    public LogsAdapter(List<LogApplicationDocument> data, OnLogListener mOnLogListener) {
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
        LogApplicationDocument logApplicationDocument = data.get(position);

        String date = logApplicationDocument.getTimestamp().toDate().toString();
        holder.getLogHour().setText(date);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<LogApplicationDocument> getData() {
        return data;
    }

    public interface OnLogListener {
        void onLogClicked(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView logHour;
        private final OnLogListener onLogListener;

        public ViewHolder(@NonNull View itemView, OnLogListener onLogListener) {
            super(itemView);
            this.onLogListener = onLogListener;
            // reference text view on log_item.xml
            this.logHour = itemView.findViewById(R.id.log_hour_textView);
            itemView.setOnClickListener(this);
        }

        public TextView getLogHour() {
            return logHour;
        }

        @Override
        public void onClick(View v) {
            onLogListener.onLogClicked(getAdapterPosition());
        }
    }
}
