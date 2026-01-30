package com.pawpasta.se182138_lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity1B extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_1_b);

        FloatingActionButton buttonBack = findViewById(R.id.button_back);
        FloatingActionButton buttonForward = findViewById(R.id.button_forward);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity1B.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity1B.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}
