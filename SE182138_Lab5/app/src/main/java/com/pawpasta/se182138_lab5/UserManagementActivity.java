package com.pawpasta.se182138_lab5;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserManagementActivity extends AppCompatActivity {

    private final List<User> users = new ArrayList<>();
    private UserManageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);
        setTitle(getString(R.string.title_manage_users));

        RecyclerView rvUsers = findViewById(R.id.rvUsers);
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        FloatingActionButton fabBack = findViewById(R.id.fabBack);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvUsers.setLayoutManager(layoutManager);
        rvUsers.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));

        users.addAll(createMockUsers(60));

        adapter = new UserManageAdapter(users, new UserManageAdapter.Listener() {
            @Override
            public void onEdit(User user, int position) {
                showUserDialog(user, position);
            }

            @Override
            public void onLongDelete(User user, int position) {
                deleteUser(position);
            }
        });

        rvUsers.setAdapter(adapter);

        fabAdd.setOnClickListener(v -> showUserDialog(null, -1));
        fabBack.setOnClickListener(v -> finish());
    }

    private void deleteUser(int position) {
        if (position < 0 || position >= users.size()) return;
        User removed = users.remove(position);
        adapter.notifyItemRemoved(position);
        Toast.makeText(this, getString(R.string.msg_deleted, removed.getUsername()), Toast.LENGTH_SHORT).show();
    }

    private void showUserDialog(User existing, int position) {
        View content = LayoutInflater.from(this).inflate(R.layout.dialog_user, null, false);
        TextInputEditText edtUsername = content.findViewById(R.id.edtUsername);
        TextInputEditText edtFullname = content.findViewById(R.id.edtFullname);
        TextInputEditText edtEmail = content.findViewById(R.id.edtEmail);

        boolean isEdit = existing != null;

        if (isEdit) {
            edtUsername.setText(existing.getUsername());
            edtFullname.setText(existing.getFullname());
            edtEmail.setText(existing.getEmail());
        }

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(isEdit ? R.string.title_edit_user : R.string.title_add_user)
                .setView(content)
                .setNegativeButton(R.string.action_cancel, (d, which) -> d.dismiss())
                .setPositiveButton(isEdit ? R.string.action_update : R.string.action_add, null)
                .create();

        dialog.setOnShowListener(d -> dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            String username = textOf(edtUsername);
            String fullname = textOf(edtFullname);
            String email = textOf(edtEmail);

            if (!isValid(username, fullname, email)) {
                Toast.makeText(this, R.string.msg_invalid_input, Toast.LENGTH_SHORT).show();
                return;
            }

            if (isEdit) {
                users.set(position, new User(username, fullname, email));
                adapter.notifyItemChanged(position);
            } else {
                int insertAt = 0; // thêm mới lên đầu list cho dễ thấy
                users.add(insertAt, new User(username, fullname, email));
                adapter.notifyItemInserted(insertAt);
            }

            dialog.dismiss();
        }));

        dialog.show();
    }

    @NonNull
    private String textOf(TextInputEditText edt) {
        return edt.getText() == null ? "" : edt.getText().toString().trim();
    }

    private boolean isValid(String username, String fullname, String email) {
        return !TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(fullname)
                && !TextUtils.isEmpty(email)
                && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private List<User> createMockUsers(int count) {
        List<User> result = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            String username = String.format(Locale.US, "manage%03d", i);
            String fullname = "User Manage " + i;
            String email = String.format(Locale.US, "%s@example.com", username);
            result.add(new User(username, fullname, email));
        }
        return result;
    }
}
