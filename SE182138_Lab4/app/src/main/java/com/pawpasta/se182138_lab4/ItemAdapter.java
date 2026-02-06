package com.pawpasta.se182138_lab4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    private static final String TAG = "Lab4/ItemAdapter";

    Context context;
    ArrayList<Item> items;
    ArrayList<Item> selectedItems = new ArrayList<>();

    public ItemAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;

        Log.d(TAG, "ItemAdapter created: items=" + (items == null ? 0 : items.size()));
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
                if (!selectedItems.contains(item)) {
                    selectedItems.add(item);
                }
            } else {
                selectedItems.remove(item);
            }

            Log.d(TAG, "Selection changed: position=" + position + ", isChecked=" + isChecked + ", selectedCount=" + selectedItems.size());
        });

        return convertView;
    }

    public ArrayList<Item> getSelectedItems() {
        Log.d(TAG, "getSelectedItems(): selectedCount=" + (selectedItems == null ? 0 : selectedItems.size()));
        return selectedItems;
    }
}
