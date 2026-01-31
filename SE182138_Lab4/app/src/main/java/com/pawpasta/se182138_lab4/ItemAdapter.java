package com.pawpasta.se182138_lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends BaseAdapter {

    Context context;
    ArrayList<Item> items;
    ArrayList<Item> selectedItems = new ArrayList<>();

    public ItemAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_view, parent, false);
        }

        CheckBox checkBox = convertView.findViewById(R.id.checkItem);
        ImageView img = convertView.findViewById(R.id.imgItem);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtDesc = convertView.findViewById(R.id.txtDesc);
        TextView txtPrice = convertView.findViewById(R.id.txtPrice);

        Item item = items.get(position);

        txtName.setText(item.getName());
        txtDesc.setText(item.getDescription());
        txtPrice.setText(item.getPrice() + " VNĐ");
        img.setImageResource(item.getImage());

        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(selectedItems.contains(item));

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedItems.add(item);
            } else {
                selectedItems.remove(item);
            }
        });

        return convertView;
    }

    public ArrayList<Item> getSelectedItems() {
        return selectedItems;
    }
}




