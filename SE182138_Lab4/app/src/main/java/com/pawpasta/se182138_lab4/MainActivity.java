package com.pawpasta.se182138_lab4;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Lab4/MainActivity";

    TextView txtFood, txtDrink;
    Button btnFood, btnDrink, btnExit;

    final int FOOD_REQUEST = 1;
    final int DRINK_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate()");

        txtFood = findViewById(R.id.txtFood);
        txtDrink = findViewById(R.id.txtDrink);
        btnFood = findViewById(R.id.btnFood);
        btnDrink = findViewById(R.id.btnDrink);
        btnExit = findViewById(R.id.btnExit);

        btnFood.setOnClickListener(v -> {
            Log.d(TAG, "Click btnFood -> open ItemActivity(type=food)");
            Intent intent = new Intent(this, ItemActivity.class);
            intent.putExtra("type", "food");
            startActivityForResult(intent, FOOD_REQUEST);
        });

        btnDrink.setOnClickListener(v -> {
            Log.d(TAG, "Click btnDrink -> open ItemActivity(type=drink)");
            Intent intent = new Intent(this, ItemActivity.class);
            intent.putExtra("type", "drink");
            startActivityForResult(intent, DRINK_REQUEST);
        });

        btnExit.setOnClickListener(v -> {
            Log.d(TAG, "Click btnExit -> finish() MainActivity");
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult(requestCode=" + requestCode + ", resultCode=" + resultCode + ", hasData=" + (data != null) + ")");

        if (resultCode == RESULT_OK && data != null) {
            String names = data.getStringExtra("names");
            int total = data.getIntExtra("total", 0);

            // Avoid logging full names to reduce noise / potential sensitive output.
            Log.d(TAG, "Result OK -> total=" + total + ", namesLength=" + (names == null ? 0 : names.length()));

            if (requestCode == FOOD_REQUEST) {
                txtFood.setText("Món ăn: " + names + "\nTổng: " + total + " VNĐ");
            }

            if (requestCode == DRINK_REQUEST) {
                txtDrink.setText("Đồ uống: " + names + "\nTổng: " + total + " VNĐ");
            }
        }
    }
}
