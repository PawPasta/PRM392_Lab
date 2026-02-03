package com.pawpasta.se182138_lab5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView rvUsers = findViewById(R.id.rvUsers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvUsers.setLayoutManager(layoutManager);
        rvUsers.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));

        List<User> users = createMockUsers(120);
        UserAdapter adapter = new UserAdapter(users, user ->
                Toast.makeText(this, user.getUsername() + " - " + user.getEmail(), Toast.LENGTH_SHORT).show());
        rvUsers.setAdapter(adapter);

        FloatingActionButton fabManage = findViewById(R.id.fabManage);
        fabManage.setOnClickListener(v -> startActivity(new Intent(this, UserManagementActivity.class)));
    }

    private List<User> createMockUsers(int count) {
        List<User> result = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            String username = String.format(Locale.US, "user%03d", i);

            // Thỉnh thoảng tạo dữ liệu dài để test wrap/ellipsize khi scroll.
            String fullname = (i % 15 == 0)
                    ? "Nguyen Van " + i + " (This is a longer fullname to demonstrate RecyclerView recycling and text wrapping)"
                    : "Nguyen Van " + i;

            String email = String.format(Locale.US, "%s@example.com", username);
            result.add(new User(username, fullname, email));
        }
        return result;
    }
}