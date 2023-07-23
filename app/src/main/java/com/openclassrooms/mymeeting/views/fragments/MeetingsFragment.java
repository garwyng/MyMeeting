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
import com.openclassrooms.mymeeting.di.DI;
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

    private MeetingRepository mMeetingRepository = DI.getMeetingRepository();
    List<Meeting> meetingsList;
    private RecyclerView mRecyclerView;
    private static MeetingsFragment mMeetingsFragment;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MeetingsFragment() {
    }

    /**
     * @return list of meetings
     */
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

    /**
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return view
     */
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
        meetingsList = mMeetingRepository.getMeetingsList();
        mRecyclerView.setAdapter(new MyMeetingsRecyclerViewAdapter(meetingsList));
    }

    @Override
    public void onResume() {
        super.onResume();
        initMeetingsList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * @param event
     */
    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        mMeetingRepository.deleteMeeting(event.meeting);
        initMeetingsList();
    }

    /**
     * @param event
     */
    @Subscribe
    public void onFilterByDate(FilterMeetingEvent event) {

        meetingsList = mMeetingRepository.dateFilter(event.dateSearch);
        mRecyclerView.setAdapter(new MyMeetingsRecyclerViewAdapter(meetingsList));
    }

    /**
     * @param event
     */
    @Subscribe
    public void onFilterByRoom(FilterRoomEvent event) {

        meetingsList = mMeetingRepository.roomFilter(event.roomSelected);
        mRecyclerView.setAdapter(new MyMeetingsRecyclerViewAdapter(meetingsList));
    }
}