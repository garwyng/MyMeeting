package com.openclassrooms.mymeeting.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mymeeting.R;

import java.util.List;

public class MailRecyclerViewAdapter extends RecyclerView.Adapter<MailRecyclerViewAdapter.ViewHolder> {

    private final List<String> mailsList;


    public MailRecyclerViewAdapter(List<String> mails) {
        mailsList = mails;
    }

    @NonNull
    @Override
    public MailRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_mail, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull MailRecyclerViewAdapter.ViewHolder holder, int position) {
        String mail = mailsList.get(position);
        holder.mTextViewMail.setText(mail);
    }

    /**
     * @return int size
     */
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
