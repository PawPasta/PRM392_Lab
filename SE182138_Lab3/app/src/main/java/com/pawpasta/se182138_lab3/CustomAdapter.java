package com.pawpasta.se182138_lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Fruit> {

    public CustomAdapter(@NonNull Context context, ArrayList<Fruit> fruits) {
        super(context, 0, fruits);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_custom, parent, false);
        }

        Fruit fruit = getItem(position);

        ImageView ivFruit = convertView.findViewById(R.id.iv_fruit);
        TextView tvFruitName = convertView.findViewById(R.id.tv_fruit_name);
        TextView tvFruitDescription = convertView.findViewById(R.id.tv_fruit_description);

        if (fruit != null) {
            ivFruit.setImageResource(fruit.getImageId());
            tvFruitName.setText(fruit.getName());
            tvFruitDescription.setText(fruit.getDescription());
        }

        return convertView;
    }
}
