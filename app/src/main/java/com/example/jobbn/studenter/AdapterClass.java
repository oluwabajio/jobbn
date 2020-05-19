package com.example.jobbn.studenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jobbn.R;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {
    ArrayList<StudentModel> list;
    public AdapterClass(ArrayList<StudentModel> list){

        this.list= list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_match,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Navn.setText(list.get(position).getNavn());
        holder.Kompetanse.setText(list.get(position).getKompetanse());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Navn, Kompetanse;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Navn= itemView.findViewById(R.id.student_navn);
            Kompetanse= itemView.findViewById(R.id.student_kompetanse);
        }
    }


}
