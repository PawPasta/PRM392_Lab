package com.pawpasta.se182138_lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView lvItems;
    private EditText etItem;
    private Button btnAdd, btnUpdate;
    private FloatingActionButton fabToCustomList;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvItems = findViewById(R.id.lv_items);
        etItem = findViewById(R.id.et_item);
        btnAdd = findViewById(R.id.btn_add);
        btnUpdate = findViewById(R.id.btn_update);
        fabToCustomList = findViewById(R.id.fab_to_custom_list);

        arrayList = new ArrayList<>(Arrays.asList("Android", "PHP", "iOS", "Unity", "ASP.NET"));

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        lvItems.setAdapter(arrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString().trim();
                if (!item.isEmpty()) {
                    arrayList.add(item);
                    arrayAdapter.notifyDataSetChanged();
                    etItem.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập item", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = etItem.getText().toString().trim();
                if (selectedPosition != -1 && !item.isEmpty()) {
                    arrayList.set(selectedPosition, item);
                    arrayAdapter.notifyDataSetChanged();
                    etItem.setText("");
                    btnAdd.setVisibility(View.VISIBLE);
                    btnUpdate.setVisibility(View.GONE);
                    selectedPosition = -1;
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn item và nhập thông tin cập nhật", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = arrayList.get(position);
                Toast.makeText(MainActivity.this, selectedItem, Toast.LENGTH_SHORT).show();
                etItem.setText(selectedItem);
                btnAdd.setVisibility(View.GONE);
                btnUpdate.setVisibility(View.VISIBLE);
                selectedPosition = position;
            }
        });

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                arrayAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Đã xóa item", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        fabToCustomList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CustomListViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
