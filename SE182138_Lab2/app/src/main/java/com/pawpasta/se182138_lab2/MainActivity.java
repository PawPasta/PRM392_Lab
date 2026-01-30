package com.pawpasta.se182138_lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextInputEditText edtMin, edtMax;
    Button btnGenerate;
    TextView txtResult;
    FloatingActionButton fabBack, fabForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtMin = findViewById(R.id.minNumber);
        edtMax = findViewById(R.id.maxNumber);
        btnGenerate = findViewById(R.id.btnGenerate);
        txtResult = findViewById(R.id.txtResult);
        fabBack = findViewById(R.id.fab_back);
        fabForward = findViewById(R.id.fab_forward);

        // Xử lý sự kiện click
        btnGenerate.setOnClickListener(v -> {

            String minStr = edtMin.getText().toString().trim();
            String maxStr = edtMax.getText().toString().trim();

            // Kiểm tra rỗng
            if (minStr.isEmpty() || maxStr.isEmpty()) {
                txtResult.setText("Please enter Min and Max");
                return;
            }

            int min = Integer.parseInt(minStr);
            int max = Integer.parseInt(maxStr);


            if (min > max) {
                txtResult.setText("Min must be less than or equal to Max");
                return;
            }


            int randomNumber = new Random().nextInt(max - min + 1) + min;


            txtResult.setText("Result: " + randomNumber);
        });

        fabBack.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity4.class));
        });

        fabForward.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity2.class));
        });

    }
}
