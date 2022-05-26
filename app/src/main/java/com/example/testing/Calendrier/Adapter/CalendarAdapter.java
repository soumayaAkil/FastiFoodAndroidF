package com.example.testing.Calendrier.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.R;
import com.example.testing.Models.commande;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.calendarViewHolder> {
    List<commande> listcj;
    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;


    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;

    }


    @NonNull
    @NotNull
    @Override
    public CalendarAdapter.calendarViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height=(int) (parent.getHeight()*0.166666666);
        return new calendarViewHolder(view,onItemListener);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CalendarAdapter.calendarViewHolder holder, int position) {
        //commande DF = listcj.get(position);
        holder.dayOfMonth.setText(daysOfMonth.get(position));


    }

    @Override
    public int getItemCount() {

        return daysOfMonth.size();
    }
    public interface OnItemListener{
        void onItemClick(int position,String dayText);

    }

    public class calendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView dayOfMonth;
        private final CalendarAdapter.OnItemListener onItemListener ;
        public calendarViewHolder(@NonNull @NotNull View itemView,CalendarAdapter.OnItemListener onItemListener) {
            super(itemView);
            dayOfMonth=itemView.findViewById(R.id.cellDayText);

            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemListener.onItemClick(getAdapterPosition(),(String) dayOfMonth.getText());
        }
    }
}
