package com.openclassrooms.mymeeting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mymeeting.databinding.FragmentAddMeetingBinding;

import java.util.ArrayList;
import java.util.List;

public class MailRecyclerViewAdapter extends RecyclerView.Adapter<MailRecyclerViewAdapter.ViewHolder> {

    private final List<String> mailsList;
    public MailRecyclerViewAdapter(List<String> mails){mailsList= mails;}

    @NonNull
    @Override
    public MailRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_mail,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MailRecyclerViewAdapter.ViewHolder holder, int position) {
        String mail = mailsList.get(position);
        holder.mTextViewMail.setText(mail);
    }

    @Override
    public int getItemCount() {
        return mailsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewMail;

        ViewHolder(View view) {
            super(view);
            mTextViewMail = view.findViewById(R.id.email);
        }
    }
}
