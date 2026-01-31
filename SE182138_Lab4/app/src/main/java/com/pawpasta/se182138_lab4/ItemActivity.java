package com.pawpasta.se182138_lab4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {

    ListView listView;
    Button btnOrder;
    ArrayList<Item> items;
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        listView = findViewById(R.id.listView);
        btnOrder = findViewById(R.id.btnOrder);

        items = new ArrayList<>();

        String type = getIntent().getStringExtra("type");

        if ("food".equals(type)) {
            items.add(new Item(R.drawable.pho, "Phở Hà Nội", "Đặc sản Hà Nội", 40000));
            items.add(new Item(R.drawable.bunbo, "Bún Bò Huế", "Cay nồng Huế", 45000));
            items.add(new Item(R.drawable.miquang, "Mì Quảng", "Đậm đà Quảng Nam", 35000));
            items.add(new Item(R.drawable.hu_tiu, "Hủ Tiếu Sài Gòn", "Ngon truyền thống", 30000));
        } else {
            items.add(new Item(R.drawable.pepsi, "Pepsi", "Nước ngọt", 15000));
            items.add(new Item(R.drawable.heniken, "Heineken", "Bia", 25000));
            items.add(new Item(R.drawable.tiger, "Tiger", "Bia Tiger", 23000));
            items.add(new Item(R.drawable.sodakem, "Soda Kem", "Nước ngọt", 20000));
        }

        adapter = new ItemAdapter(this, items);
        listView.setAdapter(adapter);

        btnOrder.setOnClickListener(v -> {
            ArrayList<Item> selected = adapter.getSelectedItems();

            if (selected.size() == 0) {
                Toast.makeText(this, "Vui lòng chọn ít nhất 1 món", Toast.LENGTH_SHORT).show();
                return;
            }

            StringBuilder names = new StringBuilder();
            int total = 0;

            for (Item i : selected) {
                names.append(i.getName()).append(", ");
                total += i.getPrice();
            }

            Intent intent = new Intent();
            intent.putExtra("names", names.toString());
            intent.putExtra("total", total);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
