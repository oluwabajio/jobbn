package com.example.jobbn;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArrayAdapter2 extends ArrayAdapter<Cards2> {


    Context context;
    List<Cards2> items;

    public ArrayAdapter2(Context context, int resourceId, List<Cards2> items){
        super(context, resourceId, items);
        this.context = context;
        this.items = items;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Cards2 card_item = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tinder_card_view, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.nameAgeTxt);

        name.setText(card_item.getName());
        Log.e("ggg", "i am happy");



        return convertView;

    }
}

