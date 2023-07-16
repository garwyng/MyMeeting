package com.openclassrooms.mymeeting.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mymeeting.R;
import com.openclassrooms.mymeeting.databinding.FragmentMeetingBinding;
import com.openclassrooms.mymeeting.events.DeleteMeetingEvent;
import com.openclassrooms.mymeeting.models.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MyMeetingsRecyclerViewAdapter extends RecyclerView
        .Adapter<MyMeetingsRecyclerViewAdapter.ViewHolder> {

    static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm", Locale.FRANCE);
    private List<Meeting> mMeetingList;
    MyMeetingsRecyclerViewAdapter mMyMeetingsRecyclerViewAdapter;

    public MyMeetingsRecyclerViewAdapter getRecyclerViewAdapterInstance(){
        if (mMyMeetingsRecyclerViewAdapter == null){
            mMyMeetingsRecyclerViewAdapter = new MyMeetingsRecyclerViewAdapter(mMeetingList);
        }
        return mMyMeetingsRecyclerViewAdapter;}

    public MyMeetingsRecyclerViewAdapter(List<Meeting> items) {
        mMeetingList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentMeetingBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeetingList.get(position);
        holder.hourMeeting.setText(format.format(mMeetingList.get(position).getStartDate().getTime()));
        holder.subjectReu.setText(mMeetingList.get(position).getSubject());
        holder.meetingRoom.setText(mMeetingList.get(position).getRoom());
        holder.meetingGuest.setText(mMeetingList.get(position).getGuests().toString());
        holder.imageMeeting.setImageResource(R.drawable.baseline_meeting_room_24);
        holder.imageDeleteMeeting.setImageResource(R.drawable.baseline_delete_24);

        holder.imageDeleteMeeting.setOnClickListener(v -> {
            EventBus.getDefault().post(new DeleteMeetingEvent(meeting));

        });
    }

    @Override
    public int getItemCount() {
        return mMeetingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView meetingRoom;
        public final TextView hourMeeting;
        public final TextView subjectReu;
        public final TextView meetingGuest;
        public final ImageView imageDeleteMeeting;
        public ImageView imageMeeting;


        public ViewHolder(FragmentMeetingBinding binding) {
            super(binding.getRoot());
            meetingRoom = binding.itemRoomName;
            hourMeeting = binding.itemMeetingHour;
            subjectReu = binding.itemListObjet;
            meetingGuest = binding.itemListGuest;
            imageMeeting = binding.itemImageListReu;
            imageDeleteMeeting = binding.itemListDeleteButton;
        }
    }
}