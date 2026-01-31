package com.pawpasta.se182138_lab4;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView txtFood, txtDrink;
    Button btnFood, btnDrink, btnExit;

    final int FOOD_REQUEST = 1;
    final int DRINK_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFood = findViewById(R.id.txtFood);
        txtDrink = findViewById(R.id.txtDrink);
        btnFood = findViewById(R.id.btnFood);
        btnDrink = findViewById(R.id.btnDrink);
        btnExit = findViewById(R.id.btnExit);

        btnFood.setOnClickListener(v -> {
            Intent intent = new Intent(this, ItemActivity.class);
            intent.putExtra("type", "food");
            startActivityForResult(intent, FOOD_REQUEST);
        });

        btnDrink.setOnClickListener(v -> {
            Intent intent = new Intent(this, ItemActivity.class);
            intent.putExtra("type", "drink");
            startActivityForResult(intent, DRINK_REQUEST);
        });

        btnExit.setOnClickListener(v -> finish());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            String names = data.getStringExtra("names");
            int total = data.getIntExtra("total", 0);

            if (requestCode == FOOD_REQUEST) {
                txtFood.setText("Món ăn: " + names + "\nTổng: " + total + " VNĐ");
            }

            if (requestCode == DRINK_REQUEST) {
                txtDrink.setText("Đồ uống: " + names + "\nTổng: " + total + " VNĐ");
            }
        }
    }
}
