package com.openclassrooms.mymeeting.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mymeeting.R;
import com.openclassrooms.mymeeting.controler.MeetingRepository;
import com.openclassrooms.mymeeting.controler.MyMeetingApiService;
import com.openclassrooms.mymeeting.events.DeleteMeetingEvent;
import com.openclassrooms.mymeeting.events.FilterMeetingEvent;
import com.openclassrooms.mymeeting.events.FilterRoomEvent;
import com.openclassrooms.mymeeting.models.Meeting;
import com.openclassrooms.mymeeting.views.adapters.MyMeetingsRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class MeetingsFragment extends Fragment {

    private final MyMeetingApiService service = MeetingRepository.getInstance();
    List<Meeting> meetingsList;
    private RecyclerView mRecyclerView;
    private static MeetingsFragment mMeetingsFragment;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MeetingsFragment() {
    }

    public static MeetingsFragment getMeetingFragmentInstance(){
        if (mMeetingsFragment == null){
            mMeetingsFragment = new MeetingsFragment();
        }
        return mMeetingsFragment;
    }

    public static MeetingsFragment newInstance() {
        MeetingsFragment fragment = new MeetingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meetings_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    private void initMeetingsList() {
        meetingsList = service.getMeetingsList();
        mRecyclerView.setAdapter(new MyMeetingsRecyclerViewAdapter(meetingsList));
    }

    @Override
    public void onResume() {
        super.onResume();
        initMeetingsList();
    }

    /**
     *
     */
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    /**
     *
     */
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        service.deleteMeeting(event.meeting);
        initMeetingsList();
    }

    @Subscribe
    public void onFilterByDate(FilterMeetingEvent event) {

        meetingsList = service.dateFilter(event.dateSearch);
        mRecyclerView.setAdapter(new MyMeetingsRecyclerViewAdapter(meetingsList));
    }

    @Subscribe
    public void onFilterByRoom(FilterRoomEvent event) {

        meetingsList = service.roomFilter(event.roomSelected);
        mRecyclerView.setAdapter(new MyMeetingsRecyclerViewAdapter(meetingsList));
    }
}