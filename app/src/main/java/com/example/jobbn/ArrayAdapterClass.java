package com.example.jobbn;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.jobbn.bedrifter.BedriftMatchResultatActivity;
import com.example.jobbn.bedrifter.cards;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manel on 9/5/2017.
 */

public class ArrayAdapterClass extends ArrayAdapter<cards> {

    Context context;
    List<cards> items;

    public ArrayAdapterClass(@NonNull Context context, int resource, @NonNull List<cards> objects) {
        super(context, resource, objects);
        this.context = context;
        this.items = objects;
    }

//    public ArrayAdapterClass(BedriftMatchResultatActivity bedriftMatchResultatActivity, ArrayList<String> nameList) {
//        this.context = context;
//        this.items = items;
//    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.tinder_card_view, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.nameAgeTxt);
        name.setText(items.get(position).getName());
        Log.e("ggg", "hhhjj"+ items.get(position).getName());
        return convertView;
    }


}
