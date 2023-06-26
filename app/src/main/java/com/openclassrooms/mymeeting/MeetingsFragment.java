package com.openclassrooms.mymeeting;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.mymeeting.controler.MyMeetingApiService;
import com.openclassrooms.mymeeting.di.DI;
import com.openclassrooms.mymeeting.events.DeleteMeetingEvent;
import com.openclassrooms.mymeeting.models.Meeting;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class MeetingsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private final MyMeetingApiService service = DI.getMyMeetingApiService();
    List<Meeting> meetingsList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MeetingsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MeetingsFragment newInstance(int columnCount) {
        MeetingsFragment fragment = new MeetingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        // Set the adapter

        return view;
    }
    private void initMeetingsList(){
        meetingsList = service.getMeetingsList();
        mRecyclerView.setAdapter(new MyMeetingsRecyclerViewAdapter(meetingsList));
    }
    @Override
    public void onResume(){
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
    public void onDeleteMeeting(DeleteMeetingEvent event){
        service.deleteMeeting(event.meeting);
        initMeetingsList();
    } 
}