package com.pawpasta.se182138_lab5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    public interface OnUserClickListener {
        void onUserClick(User user);
    }

    private final List<User> users;
    private final OnUserClickListener onUserClickListener;

    public UserAdapter(@NonNull List<User> users, OnUserClickListener onUserClickListener) {
        this.users = users;
        this.onUserClickListener = onUserClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.bind(user, onUserClickListener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvUsername;
        private final TextView tvFullname;
        private final TextView tvEmail;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvFullname = itemView.findViewById(R.id.tvFullname);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }

        void bind(@NonNull User user, OnUserClickListener listener) {
            tvUsername.setText(itemView.getContext().getString(R.string.label_username, user.getUsername()));
            tvFullname.setText(itemView.getContext().getString(R.string.label_fullname, user.getFullname()));
            tvEmail.setText(itemView.getContext().getString(R.string.label_email, user.getEmail()));

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onUserClick(user);
                }
            });
        }
    }
}
