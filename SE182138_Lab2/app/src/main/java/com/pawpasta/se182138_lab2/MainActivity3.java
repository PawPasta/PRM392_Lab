package com.pawpasta.se182138_lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

public class MainActivity3 extends AppCompatActivity {

    private EditText username, password;
    private Button btnLogin;
    private TextView tvSignUp;
    private FloatingActionButton fabBack, fabForward;

    // Hardcoded user data
    private HashMap<String, String> users = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_3);

        // Add a default user
        users.put("user", "123456");

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUp = findViewById(R.id.tvSignUp);
        fabBack = findViewById(R.id.fab_back);
        fabForward = findViewById(R.id.fab_forward);

        // Get new user data from sign up
        Intent intent = getIntent();
        if (intent.hasExtra("username") && intent.hasExtra("password")) {
            String newUser = intent.getStringExtra("username");
            String newPass = intent.getStringExtra("password");
            users.put(newUser, newPass);
            username.setText(newUser);
            password.setText(newPass);
        }

        btnLogin.setOnClickListener(v -> {
            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(MainActivity3.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (users.containsKey(user) && users.get(user).equals(pass)) {
                Toast.makeText(MainActivity3.this, "Login successful!", Toast.LENGTH_SHORT).show();
                // Navigate to the main activity on successful login
                startActivity(new Intent(MainActivity3.this, MainActivity.class));
                finish(); // Finish LoginActivity so the user can't go back to it
            } else {
                Toast.makeText(MainActivity3.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });

        tvSignUp.setOnClickListener(v -> {
            Intent signUpIntent = new Intent(MainActivity3.this, MainActivity4.class);
            startActivity(signUpIntent);
        });

        fabBack.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity2.class));
        });

        fabForward.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity4.class));
        });
    }
}
