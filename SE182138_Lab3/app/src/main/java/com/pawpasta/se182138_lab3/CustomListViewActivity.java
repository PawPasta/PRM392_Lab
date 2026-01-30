package com.pawpasta.se182138_lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CustomListViewActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView lvCustom;
    private ArrayList<Fruit> fruitList;
    private CustomAdapter customAdapter;
    private EditText etFruitName, etFruitDescription;
    private Button btnAddFruit, btnUpdateFruit;
    private FloatingActionButton fabToMain;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

        toolbar = findViewById(R.id.toolbar_custom);
        setSupportActionBar(toolbar);

        lvCustom = findViewById(R.id.lv_custom);
        etFruitName = findViewById(R.id.et_fruit_name);
        etFruitDescription = findViewById(R.id.et_fruit_description);
        btnAddFruit = findViewById(R.id.btn_add_fruit);
        btnUpdateFruit = findViewById(R.id.btn_update_fruit);
        fabToMain = findViewById(R.id.fab_to_main);

        fruitList = new ArrayList<>();
        fruitList.add(new Fruit("Chuối tiêu", "Chuối tiêu Long An", R.drawable.banana));
        fruitList.add(new Fruit("Thanh Long", "Thanh long ruột đỏ", R.drawable.dragonfruit));
        fruitList.add(new Fruit("Dâu tây", "Dâu tây Đà Lạt", R.drawable.strawberry));
        fruitList.add(new Fruit("Dưa hấu", "Dưa hấu Tiền Giang", R.drawable.watermelon));
        fruitList.add(new Fruit("Cam vàng", "Cam vàng nhập khẩu", R.drawable.orange));

        customAdapter = new CustomAdapter(this, fruitList);
        lvCustom.setAdapter(customAdapter);

        btnAddFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etFruitName.getText().toString().trim();
                String description = etFruitDescription.getText().toString().trim();
                if (!name.isEmpty() && !description.isEmpty()) {
                    fruitList.add(new Fruit(name, description, R.mipmap.ic_launcher)); // Ảnh placeholder
                    customAdapter.notifyDataSetChanged();
                    etFruitName.setText("");
                    etFruitDescription.setText("");
                } else {
                    Toast.makeText(CustomListViewActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdateFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etFruitName.getText().toString().trim();
                String description = etFruitDescription.getText().toString().trim();
                if (selectedPosition != -1 && !name.isEmpty() && !description.isEmpty()) {
                    Fruit updatedFruit = new Fruit(name, description, fruitList.get(selectedPosition).getImageId());
                    fruitList.set(selectedPosition, updatedFruit);
                    customAdapter.notifyDataSetChanged();
                    etFruitName.setText("");
                    etFruitDescription.setText("");
                    btnAddFruit.setVisibility(View.VISIBLE);
                    btnUpdateFruit.setVisibility(View.GONE);
                    selectedPosition = -1;
                } else {
                    Toast.makeText(CustomListViewActivity.this, "Vui lòng chọn item và nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvCustom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                etFruitName.setText(fruit.getName());
                etFruitDescription.setText(fruit.getDescription());
                btnAddFruit.setVisibility(View.GONE);
                btnUpdateFruit.setVisibility(View.VISIBLE);
                selectedPosition = position;
            }
        });

        lvCustom.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                fruitList.remove(position);
                customAdapter.notifyDataSetChanged();
                Toast.makeText(CustomListViewActivity.this, "Đã xóa item", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        fabToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quay lại MainActivity
                finish();
            }
        });
    }
}
