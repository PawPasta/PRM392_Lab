package com.pawpasta.se182138_lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity4 extends AppCompatActivity {

    private EditText username, password, confirmPassword;
    private Button btnSignUp;
    private FloatingActionButton fabBack, fabForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_4);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        fabBack = findViewById(R.id.fab_back);
        fabForward = findViewById(R.id.fab_forward);

        btnSignUp.setOnClickListener(v -> {
            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String confirmPass = confirmPassword.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(MainActivity4.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pass.equals(confirmPass)) {
                Toast.makeText(MainActivity4.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // In a real app, you would save the user data to a database or other persistent storage.
            // For this example, we'll just show a success message.
            Toast.makeText(MainActivity4.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
        });

        fabBack.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity3.class));
        });

        fabForward.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}
