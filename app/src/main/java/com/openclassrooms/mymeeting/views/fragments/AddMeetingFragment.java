package com.openclassrooms.mymeeting.views.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mymeeting.R;
import com.openclassrooms.mymeeting.controler.MeetingRepository;
import com.openclassrooms.mymeeting.controler.MyMeetingApiService;
import com.openclassrooms.mymeeting.databinding.FragmentAddMeetingBinding;
import com.openclassrooms.mymeeting.di.DI;
import com.openclassrooms.mymeeting.events.FilterRoomEvent;
import com.openclassrooms.mymeeting.models.Meeting;
import com.openclassrooms.mymeeting.views.adapters.MailRecyclerViewAdapter;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Fragment to create a new meeting
 */

public class AddMeetingFragment extends Fragment implements RoomListDialogFragment.OnRoomSelectedListener {

    private final List<String> mListMails = new ArrayList<>();
    private FragmentAddMeetingBinding binding;
    private TimePickerDialog picker;
    private RecyclerView mRecyclerView;
    private Calendar dateStart;
    private Calendar dateEnd;
    private String room;
    private final MeetingRepository mMeetingRepository = DI.getMeetingRepository();
    private final List<Meeting> mMeetings = mMeetingRepository.getMeetingsList();
    private String subject;
    private RoomListDialogFragment.OnRoomSelectedListener mOnRoomSelectedListener;

    private AlertDialog.Builder mBuilder;

    /**
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return view binding
     */
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAddMeetingBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    /**
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = binding.recyclerviewMail;
        binding.imageButtonAddMail.setEnabled(false);
        binding.editTextTextEmailAddress.addTextChangedListener(new TextWatcher() {
            /**
             * @param s
             * @param start
             * @param count
             * @param after
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            /**
             * @param s
             * @param start
             * @param before
             * @param count
             */
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            /**
             * @param s
             */
            @Override
            public void afterTextChanged(Editable s) {
                binding.imageButtonAddMail.setEnabled(!s.toString().isEmpty() && s.toString().contains("@"));
            }
        });
        binding.editTextDate.setOnClickListener(new View.OnClickListener() {
            private DatePickerDialog pickerDate;

            /**
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                final int day = cldr.get(Calendar.DAY_OF_MONTH);
                final int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                pickerDate = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateStart = Calendar.getInstance();
                                dateStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                dateStart.set(Calendar.MONTH, monthOfYear);
                                dateStart.set(Calendar.YEAR, year);
                                binding.textviewDateTitle.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                                dateEnd = Calendar.getInstance();
                                dateEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                dateEnd.set(Calendar.MONTH, monthOfYear);
                                dateEnd.set(Calendar.YEAR, year);
                            }
                        }, year, month, day);
                pickerDate.show();
            }
        });

        binding.buttonStart.setOnClickListener(new View.OnClickListener() {
            /**
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {

                                binding.buttonStart.setText(sHour + ":" + sMinute);
                                binding.buttonStop.setText((sHour + 1) + ":" + sMinute);
                                dateStart.set(Calendar.HOUR_OF_DAY, sHour);
                                dateStart.set(Calendar.MINUTE, sMinute);
                                dateEnd.set(Calendar.HOUR_OF_DAY, (sHour + 1));
                                dateEnd.set(Calendar.MINUTE, sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        binding.buttonStop.setOnClickListener(new View.OnClickListener() {
            /**
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            /**
                             * @param tp      the view associated with this listener
                             * @param sHour   the hour that was set
                             * @param sMinute the minute that was set
                             */
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                dateEnd.set(Calendar.HOUR_OF_DAY, sHour);
                                dateEnd.set(Calendar.MINUTE, sMinute);
                                if (dateEnd.getTime().getTime() <= dateStart.getTime().getTime()){
                                    mBuilder = new AlertDialog.Builder(getContext());
                                    mBuilder.setTitle("Date not valid").setMessage("the schedule set for the end of the meeting is lesser than the schedule for the start.").show();
                                    mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                                    }else{

                                binding.buttonStop.setText(sHour + ":" + sMinute);

                            }}
                        }, hour, minutes, true);
                picker.show();
            }
        });


        binding.imageButtonAddMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = binding.editTextTextEmailAddress.getText().toString();
                binding.editTextTextEmailAddress.setText(null);
                Log.d("mailslist", "onClick: " + mListMails);
                Log.d("mail", "onClick: " + mail);
                mListMails.add(mail);
                initMailList(mListMails);
            }
        });
        binding.buttonRoomSelect.setOnClickListener(new View.OnClickListener() {
            /**
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                RoomListDialogFragment dialog = new RoomListDialogFragment();
                dialog.setOnRoomSelectedListener(AddMeetingFragment.this);
                dialog.show(getParentFragmentManager(), "dialog");

            }
        });
        binding.EditSendMail.setOnClickListener(new View.OnClickListener() {
            /**
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                subject = binding.EditSendMail.getText().toString();
            }
        });
        binding.imageButtonSendMail.setOnClickListener(new View.OnClickListener() {
            /**
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Meeting meetingToAdd = new Meeting(mMeetings.size(), subject, mListMails, room, dateStart.getTime(), dateEnd.getTime());
                boolean checkMeetingNotExist = mMeetingRepository.getMeetingsList().contains(meetingToAdd);
                if (checkMeetingNotExist) {
                    Toast.makeText(getContext(), "Meeting already exist", Toast.LENGTH_LONG).show();
                } else {

                    mMeetingRepository.getMeetingsList().add(meetingToAdd);
                    NavHostFragment.findNavController(AddMeetingFragment.this)
                            .navigate(R.id.action_AddMeetingFragment_to_HomeFragment);

                    Toast.makeText(getContext(), "Meeting created with success", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * list of mail address set by user
     * @param listMails
     */
    public void initMailList(List<String> listMails) {
        mRecyclerView.setAdapter(new MailRecyclerViewAdapter(mListMails));
    }

    /**
     * listen room selected
     * @param event
     */
    @Subscribe
    public void onFilterRoomEvent(FilterRoomEvent event) {

        room = event.roomSelected;
        binding.buttonRoomSelect.setText(room);
    }

    /**
     * @param roomSelected
     */
    @Override
    public void sendRoomSelected(String roomSelected) {
        room = roomSelected;
        binding.buttonRoomSelect.setText(roomSelected);
    }
}