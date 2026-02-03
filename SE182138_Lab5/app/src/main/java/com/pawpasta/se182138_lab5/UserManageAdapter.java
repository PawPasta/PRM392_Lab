package com.pawpasta.se182138_lab5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserManageAdapter extends RecyclerView.Adapter<UserManageAdapter.UserViewHolder> {

    public interface Listener {
        void onEdit(User user, int position);

        void onLongDelete(User user, int position);
    }

    private final List<User> users;
    private final Listener listener;

    public UserManageAdapter(@NonNull List<User> users, Listener listener) {
        this.users = users;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_manage, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(users.get(position), position, listener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvUsername;
        private final TextView tvFullname;
        private final TextView tvEmail;
        private final ImageButton btnEdit;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvFullname = itemView.findViewById(R.id.tvFullname);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }

        void bind(@NonNull User user, int position, Listener listener) {
            tvUsername.setText(itemView.getContext().getString(R.string.label_username, user.getUsername()));
            tvFullname.setText(itemView.getContext().getString(R.string.label_fullname, user.getFullname()));
            tvEmail.setText(itemView.getContext().getString(R.string.label_email, user.getEmail()));

            btnEdit.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onEdit(user, position);
                }
            });

            itemView.setOnLongClickListener(v -> {
                if (listener != null) {
                    listener.onLongDelete(user, position);
                }
                return true;
            });
        }
    }
}
