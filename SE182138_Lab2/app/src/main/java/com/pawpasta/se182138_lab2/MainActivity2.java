package com.pawpasta.se182138_lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity2 extends AppCompatActivity {

    private EditText number1, number2;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide;
    private TextView result;
    private FloatingActionButton fabBack, fabForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        result = findViewById(R.id.result);
        fabBack = findViewById(R.id.fab_back);
        fabForward = findViewById(R.id.fab_forward);

        btnAdd.setOnClickListener(v -> calculate("+"));
        btnSubtract.setOnClickListener(v -> calculate("-"));
        btnMultiply.setOnClickListener(v -> calculate("*"));
        btnDivide.setOnClickListener(v -> calculate("/"));

        fabBack.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        fabForward.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity3.class));
        });
    }

    private void calculate(String operator) {
        String num1Str = number1.getText().toString();
        String num2Str = number2.getText().toString();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            result.setText("Result: Please enter both numbers");
            return;
        }

        double num1 = Double.parseDouble(num1Str);
        double num2 = Double.parseDouble(num2Str);
        double res = 0;

        switch (operator) {
            case "+":
                res = num1 + num2;
                break;
            case "-":
                res = num1 - num2;
                break;
            case "*":
                res = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    result.setText("Result: Cannot divide by zero");
                    return;
                }
                res = num1 / num2;
                break;
        }

        result.setText("Result: " + res);
    }
}
