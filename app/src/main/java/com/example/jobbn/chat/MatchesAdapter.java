package com.example.jobbn.chat;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.recyclerview.widget.RecyclerView;

import com.example.jobbn.Cards2;
import com.example.jobbn.R;


import java.util.List;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesViewHolders>{
    private List<Cards2> matchesList;
    private Context context;
    private String status;


    public MatchesAdapter(List<Cards2> matchesList, Context context, String status){
        this.matchesList = matchesList;
        this.context = context;
        this.status = status;
    }

    @Override
    public MatchesViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_matches, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        MatchesViewHolders rcv = new MatchesViewHolders(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(MatchesViewHolders holder, int position) {
     //   holder.mMatchId.setText(matchesList.get(position).getKey());
        holder.mMatchName.setText(matchesList.get(position).getName());
        holder.status = status;
        Log.e("ggg", "Name is "+ matchesList.get(position).getName() + "key is "+ matchesList.get(position).getKey());

    }

    @Override
    public int getItemCount() {
        return this.matchesList.size();
    }




}

