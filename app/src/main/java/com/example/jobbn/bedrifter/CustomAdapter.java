package com.example.jobbn.bedrifter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jobbn.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<String> nameList;

    public CustomAdapter(Context context, ArrayList<String> nameList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.nameList = nameList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.serial_number.setText(nameList.get(position));
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView serial_number;

        public MyViewHolder(View itemView) {
            super(itemView);
            serial_number = (TextView) itemView.findViewById(R.id.txtStudentName);
        }
    }
}