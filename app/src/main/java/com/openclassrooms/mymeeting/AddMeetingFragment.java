package com.openclassrooms.mymeeting;

import static android.R.*;
import static android.R.layout.*;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.mymeeting.controler.MyMeetingApiService;
import com.openclassrooms.mymeeting.databinding.FragmentAddMeetingBinding;
import com.openclassrooms.mymeeting.di.DI;
import com.openclassrooms.mymeeting.models.Meeting;
import com.openclassrooms.mymeeting.models.Room;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddMeetingFragment extends Fragment {

    private FragmentAddMeetingBinding binding;
    private TimePickerDialog picker;
    private final List<String> mListMails = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private Date dateStart;
    private Date dateEnd;
    private String[] rooms= {"Réunion 1","Réunion 2","Réunion 3","Réunion 4","Réunion 5","Réunion 6","Réunion 7","Réunion 8","Réunion 9","Réunion 10"};
    private MyMeetingApiService service = DI.getMyMeetingApiService();
    private List<Meeting> mMeetings = service.getMeetingsList();
    private String subject;
    private Room room;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAddMeetingBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = binding.recyclerviewMail;
        binding.imageButtonAddMail.setEnabled(false);
        binding.editTextTextEmailAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.imageButtonAddMail.setEnabled(!s.toString().isEmpty() && s.toString().contains("@"));
            }
        });

        binding.buttonStart.setOnClickListener(new View.OnClickListener() {
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
                                dateStart.setHours(sHour);
                                dateStart.setMinutes(sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        binding.buttonStop.setOnClickListener(new View.OnClickListener() {
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
                                binding.buttonStop.setText(sHour+":"+sMinute);
                                dateEnd.setHours(sHour);
                                dateEnd.setMinutes(sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
        binding.editTextDate.setOnClickListener(new View.OnClickListener() {
            private DatePickerDialog pickerDate;

            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                pickerDate = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                binding.editTextDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                dateStart = cldr.getTime();
                                dateEnd = cldr.getTime();
                            }
                        }, year, month, day);
                pickerDate.show();
            }
        });

        binding.imageButtonAddMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail= binding.editTextTextEmailAddress.getText().toString();
                binding.editTextTextEmailAddress.setText(null);
                Log.d("mailslist", "onClick: "+mListMails);
                Log.d("mail", "onClick: "+ mail);
                mListMails.add(mail);
                initMailList(mListMails);
            }
        });
        Spinner spinner = binding.spinner;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), simple_spinner_item, rooms);
        adapter.setDropDownViewResource(simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = rooms[position];
                room = new Room(selectedItem);
                Log.d("selectedPosition", "onItemSelected: "+ selectedItem);
                Log.d("room", "onItemSelected: "+ room);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.EditSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject = binding.EditSendMail.getText().toString();
            }
        });
        binding.imageButtonSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.getMeetingsList().add(new Meeting(mMeetings.size(),subject,mListMails,room,dateStart,dateEnd));

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void initMailList(List<String> listMails){
        mRecyclerView.setAdapter(new MailRecyclerViewAdapter(mListMails));
    }
}